package com.srihi.jcdecaux.velostations.ui.stations.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.srihi.jcdecaux.domain.model.Station
import com.srihi.jcdecaux.velostations.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_stations_list.*


/**
 * Show List of stations near the user
 */
@AndroidEntryPoint
class StationsListFragment : Fragment(), VeloStationListAdapter.OnItemClickListener {

    private val veloStationsViewModel: VeloStationsListViewModel by viewModels()
    private lateinit var veloStationListAdapter: VeloStationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        veloStationListAdapter = VeloStationListAdapter(requireContext())
        veloStationListAdapter.onItemClickListener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stations_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() {
        stations_list_view.layoutManager = LinearLayoutManager(context)
        stations_list_view.adapter = veloStationListAdapter
    }

    private fun initObservers() {
        veloStationsViewModel.itemsFragmentState.observe(
            viewLifecycleOwner,
            Observer { state -> onStateChange(state) })
    }

    private fun onStateChange(fragmentState: FragmentState) =
        when (fragmentState) {
            FragmentState.Loading -> showLoadingState()
            is FragmentState.Loaded -> showLoadedState(fragmentState)
            is FragmentState.Error -> showErrorState(fragmentState)
        }

    private fun showLoadingState() {

        progress_bar_loading_items.visibility = View.VISIBLE
        text_messages_no_data.visibility = View.GONE
    }

    private fun showLoadedState(loaded: FragmentState.Loaded) {

        progress_bar_loading_items.visibility = View.GONE
        if (loaded.stations.isEmpty()) {
            text_messages_no_data.visibility = View.VISIBLE
            text_messages_no_data.text = getString(R.string.empty_folder)
        } else {
            text_messages_no_data.visibility = View.GONE
            veloStationListAdapter.station = loaded.stations
            veloStationListAdapter.notifyDataSetChanged()
        }
    }

    private fun showErrorState(error: FragmentState.Error) {

        progress_bar_loading_items.visibility = View.GONE
        text_messages_no_data.visibility = View.VISIBLE
        text_messages_no_data.text = getString(R.string.empty_folder)
    }

    sealed class FragmentState {
        object Loading : FragmentState()
        class Loaded(val stations: List<Station>) : FragmentState()
        class Error(val message: String) : FragmentState()
    }

    override fun onItemClick(station: Station) {
        goToStationDetails(station.number, station.contract_name)
    }

    /**
     * To navigate to details fragment
     */
    private fun goToStationDetails(number: Long, contract: String) {
        val action =
            StationsListFragmentDirections.actionListFragmentToDetailFragment2(number, contract)
        Navigation.findNavController(stations_list_view).navigate(action)
    }
}