package cl.konadev.dogbreedviewer.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cl.konadev.dogbreedviewer.R
import cl.konadev.dogbreedviewer.base.Respuesta
import cl.konadev.dogbreedviewer.repo.DataSource
import cl.konadev.dogbreedviewer.repo.RepoImpl
import cl.konadev.dogbreedviewer.view.adapter.DogImageAdapter
import cl.konadev.dogbreedviewer.view.adapter.MainAdapter
import cl.konadev.dogbreedviewer.viewmodel.DogBreedViewModel
import cl.konadev.dogbreedviewer.viewmodel.VMFactory
import kotlinx.android.synthetic.main.fragment_dog_image.*
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber

class DogImageFragment : Fragment(){

    private val viewModel by viewModels<DogBreedViewModel> { VMFactory(RepoImpl(DataSource())) }

    private lateinit var dogBreedString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            dogBreedString = it.getString("dog_breed")!!

            Timber.e("DOGBREED: $dogBreedString")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dog_image, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.setDogBreed(dogBreedString)



        setUpRecyclerView()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.fetchDogBreedImg.observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Respuesta.Loading -> progress_bar_image.visibility = View.VISIBLE

                is Respuesta.Success -> {
                    progress_bar_image.visibility = View.GONE

                    if (result.data.status == "success") {
                        Timber.e("DATA: ${result.data.message}")
                        recycler_dogbreed_image.adapter = DogImageAdapter(requireContext(), result.data.message)
                    } else {
                        Toast.makeText(requireContext(),"No hay data disponible", Toast.LENGTH_SHORT).show()
                    }
                }

                is Respuesta.Failure -> {
                    progress_bar.visibility = View.GONE
                    Timber.e(result.exception)
                    Toast.makeText(requireContext(),"Ocurrío un problema ${result.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setUpRecyclerView() {
        recycler_dogbreed_image.layoutManager = GridLayoutManager(requireContext(),2)
    }

}