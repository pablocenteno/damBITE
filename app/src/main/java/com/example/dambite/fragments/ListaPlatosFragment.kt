package com.example.dambite.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dambite.R
import com.example.dambite.databinding.FragmentListaPlatosBinding
import com.example.dambite.entity.Plato
import com.example.dambite.recyclerview.PlatoRVAdapter
import com.example.dambite.rest.ListaDePlatosResponse
import com.example.dambite.rest.PlatoResponse
import com.example.dambite.rest.RetrofitInstance
import com.example.dambite.viewModel.FavoritosViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListaPlatosFragment : Fragment() {

    private var binding: FragmentListaPlatosBinding? = null
    private var listaPlatos: List<PlatoResponse> = mutableListOf<PlatoResponse>()
    private lateinit var listaPlatosAdapter: PlatoRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentListaPlatosBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        initRecyclerView()
        return fragmentBinding.root
    }

    fun initRecyclerView() {
        //Declaramos el adapatador del recyclerview
        listaPlatosAdapter = PlatoRVAdapter(listaPlatos = listaPlatos,
            perfilPlato = { plato -> perfilPlato(plato) },
            anadirFavorito = { plato -> anadirFavorito(plato) })
        //Establecemos su layoutmanager y el adaptador
        binding!!.listaPlatosRV.layoutManager = LinearLayoutManager(requireContext())
        binding!!.listaPlatosRV.adapter = listaPlatosAdapter
    }

    fun perfilPlato(plato: PlatoResponse) {
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

    fun anadirFavorito(plato: PlatoResponse) {
        //Rellena codigo
    }





}