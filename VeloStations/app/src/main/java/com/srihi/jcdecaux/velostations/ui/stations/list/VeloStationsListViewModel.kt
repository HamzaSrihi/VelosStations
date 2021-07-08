package com.srihi.jcdecaux.velostations.ui.stations.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srihi.jcdecaux.domain.model.Station
import com.srihi.jcdecaux.domain.usecases.DataRepositorySource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class VeloStationsListViewModel @Inject constructor(private val dataRepositorySource: DataRepositorySource) :
    ViewModel() {

    private var disposable: Disposable? = null

    private val _itemsFragmentState = MutableLiveData<StationsListFragment.FragmentState>()
    val itemsFragmentState: LiveData<StationsListFragment.FragmentState> = _itemsFragmentState

    init {

        _itemsFragmentState.value = StationsListFragment.FragmentState.Loading

        disposable = loadStations()
    }

    private fun loadStations() =
        dataRepositorySource.getStationsByContract("lyon")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)

    private fun onSuccess(stations: List<Station>) {
        _itemsFragmentState.value = StationsListFragment.FragmentState.Loaded(stations)
    }

    private fun onError(throwable: Throwable) {
        _itemsFragmentState.value = StationsListFragment.FragmentState.Error(throwable.toString())
    }


    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}