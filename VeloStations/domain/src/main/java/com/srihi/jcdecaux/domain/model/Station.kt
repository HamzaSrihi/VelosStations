package com.srihi.jcdecaux.domain.model

/**
 *
 *  @property number
 * @property contract_name
 * @property name
 * @property address
 * @property banking
 * @property bonus
 * @property bike_stands
 * @property available_bike_stands
 * @property available_bikes
 * @property status
 * @property last_update
 */
data class Station(
    val number: Long,
    val contract_name: String,
    val name: String,
    val address: String,
    val banking: Boolean,
    val bonus: Boolean,
    val bike_stands: Int,
    val available_bike_stands: Int,
    val available_bikes: Int,
    val status: String,
    val last_update: Long
)