package com.srihi.jcdecaux.data.repository

import com.srihi.jcdecaux.data.api.ApiService
import com.srihi.jcdecaux.domain.model.Station
import com.srihi.jcdecaux.domain.usecases.DataRepositorySource
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


class VeloStationRepository @Inject constructor(private val apiService: ApiService) : DataRepositorySource {

    override fun getStationsByContract(contract: String): Observable<List<Station>> {
        return apiService.getStations(contract)
    }

    override fun getStationsByNumber(number: Long, contract: String): Single<Station> {
        return apiService.getStationDetails(number, contract)
    }
}