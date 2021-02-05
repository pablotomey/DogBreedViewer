package cl.konadev.dogbreedviewer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cl.konadev.dogbreedviewer.R
import cl.konadev.dogbreedviewer.base.Respuesta
import cl.konadev.dogbreedviewer.repo.DataSource
import cl.konadev.dogbreedviewer.repo.RepoImpl
import cl.konadev.dogbreedviewer.view.adapter.MainAdapter
import cl.konadev.dogbreedviewer.viewmodel.DogBreedViewModel
import cl.konadev.dogbreedviewer.viewmodel.VMFactory
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber

class MainFragment : Fragment(), MainAdapter.OnDogBreedClickListener {

    private val viewModel by viewModels<DogBreedViewModel> { VMFactory(RepoImpl(DataSource())) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.getDogBreedData().observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Respuesta.Loading -> progress_bar.visibility = View.VISIBLE

                is Respuesta.Success -> {
                    progress_bar.visibility = View.GONE

                    if (result.data.status == "success") {
                        recycler_dogbreed.adapter = MainAdapter(requireContext(), result.data.message,this)
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

        recycler_dogbreed.layoutManager = LinearLayoutManager(requireContext())
        recycler_dogbreed.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

    override fun onDogBreedClick(string: String, position: Int) {
        Timber.d(string)

        val bundle = Bundle()
        bundle.putString("dog_breed", string)

        findNavController().navigate(R.id.action_mainFragment_to_dogImageFragment, bundle)
    }
}