package com.android45.covidretrofitcoroutine

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var list = listOf<CovidModel>()

        CoroutineScope(IO).launch {
            withContext(IO)
            {
                list = getCovid()
            }
            withContext(Main)
            {
                findViewById<TextView>(R.id.tvCountryCode).setText("Country Code: " + list.get(0).countryCode)
                findViewById<TextView>(R.id.tvCountry).setText("Country: " + list.get(0).country)
                findViewById<TextView>(R.id.tvLat).setText("Lat: " + list.get(0).lat)
                findViewById<TextView>(R.id.tvLng).setText("Lng: " + list.get(0).lng)
                findViewById<TextView>(R.id.tvTotalConfirmed).setText("Total Confirmed: " + list.get(0).totalConfirmed)
                findViewById<TextView>(R.id.tvTotalDeaths).setText("Total Deaths: " + list.get(0).totalDeaths)
                findViewById<TextView>(R.id.tvTotalRecovered).setText("Total Recovered: " + list.get(0).totalRecovered)
                findViewById<TextView>(R.id.tvDailyConfirmed).setText("Daily Confirmed: " + list.get(0).dailyConfirmed)
                findViewById<TextView>(R.id.tvDailyDeaths).setText("Daily Deaths: " + list.get(0).dailyDeaths)
                findViewById<TextView>(R.id.tvActiveCase).setText("Active Case: " + list.get(0).activeCases)
                findViewById<TextView>(R.id.tvTotalCritical).setText("Total Critical: " + list.get(0).totalCritical)
                findViewById<TextView>(R.id.tvTotalConfirmedPerMillionPopulation).setText("Total Confirmed Per Million Population: " + list.get(0).totalConfirmedPerMillionPopulation)
                findViewById<TextView>(R.id.tvTotalDeathsPerMillionPopulation).setText("Total Deaths Per Million Population: " + list.get(0).totalDeathsPerMillionPopulation)
                findViewById<TextView>(R.id.tvFR).setText("FR: " + list.get(0).FR)
                findViewById<TextView>(R.id.tvPR).setText("PR: " + list.get(0).PR)
                findViewById<TextView>(R.id.tvLastUpdated).setText("Last Updated: " + list.get(0).lastUpdated)
            }

        }

    }

    suspend fun getCovid(): List<CovidModel> {
        var list: List<CovidModel> = listOf()
        try {
            list = Retrofit.Builder()
                .baseUrl("https://api.coronatracker.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CovidAPI::class.java).getCovidModel().body() ?: listOf()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

//    fun getGlobalAPI() {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.coronatracker.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val covidAPI = retrofit.create(CovidAPI::class.java)
//        val call: Call<List<CovidModel>> = covidAPI.getCovidModel()
//        call.enqueue(object : Callback<List<CovidModel>> {
//            override fun onResponse(
//                call: Call<List<CovidModel>>,
//                response: Response<List<CovidModel>>
//            ) {
//                var list: List<CovidModel> = listOf()
//                list = response.body()!!
//
//
//                findViewById<TextView>(R.id.tvCountryCode).setText("Country Code: " + list.get(0).countryCode)
//                findViewById<TextView>(R.id.tvCountry).setText("Country: " + list.get(0).country)
//                findViewById<TextView>(R.id.tvLat).setText("Lat: " + list.get(0).lat)
//                findViewById<TextView>(R.id.tvLng).setText("Lng: " + list.get(0).lng)
//                findViewById<TextView>(R.id.tvTotalConfirmed).setText(
//                    "Total Confirmed: " + list.get(
//                        0
//                    ).totalConfirmed
//                )
//                findViewById<TextView>(R.id.tvTotalDeaths).setText("Total Deaths: " + list.get(0).totalDeaths)
//                findViewById<TextView>(R.id.tvTotalRecovered).setText(
//                    "Total Recovered: " + list.get(
//                        0
//                    ).totalRecovered
//                )
//                findViewById<TextView>(R.id.tvDailyConfirmed).setText(
//                    "Daily Confirmed: " + list.get(
//                        0
//                    ).dailyConfirmed
//                )
//                findViewById<TextView>(R.id.tvDailyDeaths).setText("Daily Deaths: " + list.get(0).dailyDeaths)
//                findViewById<TextView>(R.id.tvActiveCase).setText("Active Case: " + list.get(0).activeCases)
//                findViewById<TextView>(R.id.tvTotalCritical).setText("Total Critical: " + list.get(0).totalCritical)
//                findViewById<TextView>(R.id.tvTotalConfirmedPerMillionPopulation).setText(
//                    "Total Confirmed Per Million Population: " + list.get(
//                        0
//                    ).totalConfirmedPerMillionPopulation
//                )
//                findViewById<TextView>(R.id.tvTotalDeathsPerMillionPopulation).setText(
//                    "Total Deaths Per Million Population: " + list.get(
//                        0
//                    ).totalDeathsPerMillionPopulation
//                )
//                findViewById<TextView>(R.id.tvFR).setText("FR: " + list.get(0).FR)
//                findViewById<TextView>(R.id.tvPR).setText("PR: " + list.get(0).PR)
//                findViewById<TextView>(R.id.tvLastUpdated).setText("Last Updated: " + list.get(0).lastUpdated)
//
//            }
//
//            override fun onFailure(call: Call<List<CovidModel>>, t: Throwable) {}
//        })
//    }
}