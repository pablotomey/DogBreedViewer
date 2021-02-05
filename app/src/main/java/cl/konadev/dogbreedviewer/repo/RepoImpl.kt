package cl.konadev.dogbreedviewer.repo

import cl.konadev.dogbreedviewer.base.Respuesta
import cl.konadev.dogbreedviewer.model.DogBreedResponse

class RepoImpl(private val dataSource: DataSource): Repo {
    override suspend fun getDogBreedList(): Respuesta<DogBreedResponse> {
        return dataSource.getDogBreedList()
    }

    override suspend fun getDogBreedImage(dogBreed: String): Respuesta<DogBreedResponse> {
        return dataSource.getDogImage(dogBreed)
    }
}