package cl.konadev.dogbreedviewer.network

import cl.konadev.dogbreedviewer.model.DogBreedResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    @GET("list")
    suspend fun getDogBreedList(): DogBreedResponse

    @GET("{dogBreed}/image")
    suspend fun getDogImage(@Path("dogBreed") dogBreed: String): DogBreedResponse
}