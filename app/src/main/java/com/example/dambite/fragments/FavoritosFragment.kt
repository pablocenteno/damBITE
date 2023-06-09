package com.example.dambite.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dambite.R
import com.example.dambite.databinding.FragmentFavoritosBinding
import com.example.dambite.entity.Plato
import com.example.dambite.fragment.PerfilPlatoFragment
import com.example.dambite.opensqlite.MiBDOpenHelper
import com.example.dambite.recyclerview.FavoritosRVAdapter
import com.example.dambite.viewModel.FavoritosViewModel

class FavoritosFragment : Fragment() {

    private var binding: FragmentFavoritosBinding? = null
    val favoritosVM: FavoritosViewModel by activityViewModels()
    private var bd:MiBDOpenHelper?=null

    private lateinit var favoritosAdapter: FavoritosRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFavoritosBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        initRecyclerView()
        return fragmentBinding.root
    }


    fun perfilPlato(plato: Plato) {
        Toast.makeText(
            requireContext(), "El plato seleccionado es ${plato.nombre} ", Toast.LENGTH_LONG
        ).show()

        val bundle = Bundle().apply {
            putString("idPlato", plato.id)
        }
        val fragment = PerfilPlatoFragment().apply { arguments = bundle }
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }
    fun initRecyclerView(){

        val recyclerView = binding?.listaFavoritosRV
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())

        favoritosAdapter= FavoritosRVAdapter()
        favoritosAdapter.FavoritosRVAdapter(requireContext(),favoritosVM.listaPlatosFavoritos )
        recyclerView?.adapter=favoritosAdapter

    }

    fun eliminarDeFavoritos(plato: Plato){

        favoritosVM.listaPlatosFavoritos.value?.remove(plato)

        bd?.eliminarFavorito(plato)
    }


}