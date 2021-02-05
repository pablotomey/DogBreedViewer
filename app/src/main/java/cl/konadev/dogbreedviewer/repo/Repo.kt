package cl.konadev.dogbreedviewer.repo

import cl.konadev.dogbreedviewer.base.Respuesta
import cl.konadev.dogbreedviewer.model.DogBreedResponse

interface Repo {

    suspend fun getDogBreedList(): Respuesta<DogBreedResponse>
    suspend fun getDogBreedImage(dogBreed: String): Respuesta<DogBreedResponse>
}