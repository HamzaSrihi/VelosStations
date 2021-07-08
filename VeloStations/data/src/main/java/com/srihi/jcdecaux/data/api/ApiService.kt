package com.srihi.jcdecaux.data.api

import com.srihi.jcdecaux.domain.model.Station
import io.reactivex.Observable
import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val API_KEY = "38737ea0b955995e392a486923bd0b1d31c9d5a0"
    }

    @GET("stations")
    fun getStations(
        @Query("contract") contractName: String?,
        @Query("apiKey") apiKey: String = API_KEY
    ): Observable<List<Station>>

    @GET("stations/{number}")
    fun getStationDetails(
        @Path("number") stationNumber: Long?,
        @Query("contract") contractName: String?,
        @Query("apiKey") apiKey: String = API_KEY
    ): Single<Station>
}