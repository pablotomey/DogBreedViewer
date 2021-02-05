package cl.konadev.dogbreedviewer.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConexionClient {

    val service by lazy {
        Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}