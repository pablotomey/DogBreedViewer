package cl.konadev.dogbreedviewer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.konadev.dogbreedviewer.repo.Repo
import cl.konadev.dogbreedviewer.repo.RepoImpl

class VMFactory(private val repo: Repo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repo::class.java).newInstance(repo)
    }
}