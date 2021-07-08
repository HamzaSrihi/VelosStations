package com.srihi.jcdecaux.velostations.ui.stations.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.srihi.jcdecaux.domain.model.Station
import com.srihi.jcdecaux.velostations.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var stationNumber: Long? = null
    private var contractName: String? = null
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            stationNumber = DetailFragmentArgs.fromBundle(it).stationNumber
            contractName = DetailFragmentArgs.fromBundle(it).contractName
        }

        if (stationNumber != null) {
            updateStationForContract(stationNumber!!, contractName!!)
        }

        observeViewModel()
    }


    fun updateStationForContract(number: Long, contractName: String) {
        viewModel.getStationDetail(number, contractName)
    }

    /**
     * Function to observe the change in viewModel
     */
    fun observeViewModel() {

        viewModel.station.observe(this, Observer { it ->
            updateUI(it)
        })
    }

    /**
     * Method to update UI
     *
     * @param station
     */
    fun updateUI(station: Station) {
        name.text = station.name
        address.text = station.address
        bike_stands.text = station.bike_stands.toString()
        available_bikes.text = station.available_bikes.toString()
        available_bike_stands.text = station.available_bike_stands.toString()
    }
}