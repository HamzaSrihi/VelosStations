package com.srihi.jcdecaux.velostations.ui.stations.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srihi.jcdecaux.domain.model.Station
import com.srihi.jcdecaux.domain.usecases.DataRepositorySource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val dataRepositorySource: DataRepositorySource) :
    ViewModel() {
    private var disposable: Disposable? = null
    val station = MutableLiveData<Station>()

    fun getStationDetail(number: Long?, contract: String?) {
        disposable = dataRepositorySource.getStationsByNumber(number!!, contract!!)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                station.postValue(it)
            }, {

            })
    }


    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}