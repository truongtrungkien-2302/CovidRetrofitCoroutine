package com.android45.covidretrofitcoroutine

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CovidAPI {
    @GET("v3/stats/worldometer/country?countryCode=VN")
    suspend fun getCovidModel(): Response<List<CovidModel>>

}