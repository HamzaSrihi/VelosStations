package com.srihi.jcdecaux.domain.usecases

import com.srihi.jcdecaux.domain.model.Station
import io.reactivex.Observable
import io.reactivex.Single

interface DataRepositorySource {
    fun getStationsByContract(contract: String): Observable<List<Station>>
    fun getStationsByNumber(number: Long, contract: String): Single<Station>
}