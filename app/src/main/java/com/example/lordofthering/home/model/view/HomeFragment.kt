package com.example.lordofthering.home.model.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lordofthering.databinding.FragmentHomeBinding
import com.example.lordofthering.firebase.FirebaseClass
import com.example.lordofthering.home.model.api.GetDataViewModel
import com.example.lordofthering.home.model.datamodel.Doc
import com.example.lordofthering.home.model.datamodel.Moview


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewmodelHome:GetDataViewModel by viewModels()
    private val viewmodelFirebase:FirebaseClass by viewModels()
    private val adapterHome by lazy { AdapterHome() }
    private var listInfo:List<Doc> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater, container, false)



        viewmodelHome.getData()

        dataFirebase()

        return binding.root
    }
    fun dataFirebase(){
            viewmodelFirebase.firebaseData.observe(viewLifecycleOwner, Observer { imageList ->
           // Log.e("Exito", it.toString())
                adapterHome.getContexts(requireContext(), requireActivity())
                getMovies(imageList)
        })

    }

    fun getMovies(imageList: MutableList<Moview>) {
        val listMovies = mutableListOf<Moview>()
        viewmodelHome.dataMovies.observe(viewLifecycleOwner, Observer { movies ->

           movies.forEach { movie ->
               imageList.forEach{ images ->
                   run {
                       if (movie.name == images.name) {
                           listMovies.add(Moview(id = movie._id, name = movie.name, image = images.image))
                       }
                   }

               }
           }
            adapterHome.differ.submitList(listMovies)
            adapterHome.getListDoc(movies)
            listInfo = movies

        })
        inflateRecycler()
    }

    fun inflateRecycler(){
        binding.apply {
            rvMovies.apply {
                layoutManager = LinearLayoutManager(activity)
                this.hasFixedSize()
                this.adapter = adapterHome
            }
        }
    }


}