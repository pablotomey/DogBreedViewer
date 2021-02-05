package cl.konadev.dogbreedviewer.viewmodel

import androidx.lifecycle.*
import cl.konadev.dogbreedviewer.base.Respuesta
import cl.konadev.dogbreedviewer.model.DogBreedResponse
import cl.konadev.dogbreedviewer.repo.Repo
import kotlinx.coroutines.Dispatchers

class DogBreedViewModel(private val repo: Repo): ViewModel() {

    private val dogBreed = MutableLiveData<String>()

    init {
        // Inicilizamos con la consulta para traer el listado de DogBreeds
        getDogBreedData()
    }

    // Devuelve lista de DogBreed desde el repo
    fun getDogBreedData() = liveData(Dispatchers.IO) {
        emit(Respuesta.Loading())
        try {
            emit(repo.getDogBreedList())
        }catch (e: Exception) {
            emit(Respuesta.Failure(e))
        }
    }

    // Devuelve url de imágenes de DogBreed desde el repo
    val fetchDogBreedImg = dogBreed.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO){
            emit(Respuesta.Loading())
            try {
                emit(repo.getDogBreedImage(it))
            }catch (e: Exception) {
               emit(Respuesta.Failure(e))
            }
        }
    }
}