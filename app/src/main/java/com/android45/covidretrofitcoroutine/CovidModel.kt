package com.android45.covidretrofitcoroutine

import java.util.*

data class CovidModel(
    val countryCode: String,
    val country: String,
    val lat: Double,
    val lng: Double,
    val totalConfirmed: Long,
    val totalDeaths: Long,
    val totalRecovered: Long,
    val dailyConfirmed: Long,
    val dailyDeaths: Long,
    val activeCases: Long,
    val totalCritical: Long,
    val totalConfirmedPerMillionPopulation: Long,
    val totalDeathsPerMillionPopulation: Long,
    val FR: Double,
    val PR: Double,
    val lastUpdated: Date
)
