package cl.konadev.dogbreedviewer.network

import cl.konadev.dogbreedviewer.model.DogBreedResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    @GET("breeds/list")
    suspend fun getDogBreedList(): DogBreedResponse

    @GET("breed/{dogBreed}/images")
    suspend fun getDogImage(@Path("dogBreed") dogBreed: String): DogBreedResponse
}