package cl.konadev.dogbreedviewer.repo

import cl.konadev.dogbreedviewer.base.Respuesta
import cl.konadev.dogbreedviewer.model.DogBreedResponse
import cl.konadev.dogbreedviewer.network.ConexionClient

class DataSource {

    suspend fun getDogBreedList(): Respuesta<DogBreedResponse> {
        return  Respuesta.Success(ConexionClient.service.getDogBreedList())
    }

    suspend fun getDogImage(dogBreed: String): Respuesta<DogBreedResponse> {
        return Respuesta.Success(ConexionClient.service.getDogImage(dogBreed))
    }
}