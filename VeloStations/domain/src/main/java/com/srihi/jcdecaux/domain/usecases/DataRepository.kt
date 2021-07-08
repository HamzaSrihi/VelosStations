package com.srihi.jcdecaux.domain.usecases

import com.srihi.jcdecaux.domain.model.Station
import io.reactivex.Observable
import io.reactivex.Single

class DataRepository(private val dataRepositorySource: DataRepositorySource) {
    fun getStationsByContract(contract: String): Observable<List<Station>> {
        return dataRepositorySource.getStationsByContract(contract)
    }

    fun fetchStationsByNumber(number: Long, contract: String): Single<Station> {
        return dataRepositorySource.getStationsByNumber(number, contract)
    }
}