package com.srihi.jcdecaux.domain

import com.srihi.jcdecaux.domain.model.Station
import com.srihi.jcdecaux.domain.usecases.DataRepository
import com.srihi.jcdecaux.domain.usecases.DataRepositorySource
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.assertj.core.api.Assertions
import org.junit.Test
import org.mockito.Mockito

class DataRepositoryTest {

    //mock gateway
    private val dataRepositorySource =
        Mockito.mock(DataRepositorySource::class.java)
    private val stationsByContractUseCase =
        DataRepository(
            dataRepositorySource
        )


    private val contract = "lyon"

    private val station_1 = Station(
        9087, "lyon", "2010-CONFLUENCE/DARSE",
        "MAZARGUES - ROND POINT DE MAZARGUES (OBELISQUE)", true, false, 21,
        12, 9, "OPEN", 1601332173000
    )

    private val station_2 = Station(
        8149, "lyon", "5015-FULCHIRON",
        "391 MICHELET - 391 BOULEVARD MICHELET", true, false, 19,
        5, 14, "OPEN", 1601332266000
    )

    private val stations = mutableListOf(station_1, station_2)

    @Test
    fun success() {
        Mockito.`when`(dataRepositorySource.getStationsByContract(contract))
            .thenReturn(Observable.just(stations))

        val testObserver = TestObserver<List<Station>>()
        val observable = stationsByContractUseCase.getStationsByContract(contract)

        observable.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()

        Assertions.assertThat(testObserver.values())
            .containsExactly(stations)

        Mockito.verify(dataRepositorySource, Mockito.times(1))
            .getStationsByContract(contract)
        Mockito.verifyNoMoreInteractions(dataRepositorySource)
    }


    @Test
    fun error() {
        val testException = Exception()
        Mockito.`when`(dataRepositorySource.getStationsByContract(contract))
            .thenReturn(Observable.error(testException))

        val valueResponseObservable = stationsByContractUseCase.getStationsByContract(contract)
        val testObserver = TestObserver<List<Station>>()

        valueResponseObservable.subscribe(testObserver)

        Mockito.verify(dataRepositorySource).getStationsByContract(contract)
        Mockito.verifyNoMoreInteractions(dataRepositorySource)

        testObserver.assertError(testException)
    }
}