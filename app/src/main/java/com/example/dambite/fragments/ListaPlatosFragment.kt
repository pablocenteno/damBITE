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
    private val favoritosViewModel: FavoritosViewModel by activityViewModels()


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

        binding!!.btnBuscar.setOnClickListener { obtenerPlatos() }
        obtenerPlatos()
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

        var favorito = Plato(
            plato.id,
            plato.nombre,
            plato.categoria,
            plato.area,
            plato.urlImagen
        )

        if (favoritosViewModel.anadirFavorito(favorito)) {
            binding!!.listaPlatosRV.adapter!!.notifyDataSetChanged()

            Toast.makeText(
                requireContext(), " ${plato.nombre}  añadido a favoritos", Toast.LENGTH_LONG
            ).show()
        }else{
            Toast.makeText(
                requireContext(), " ${plato.nombre}  ya esta añadido a favoritos", Toast.LENGTH_LONG
            ).show()
        }
    }


    fun obtenerPlatos() {
        var texto = binding!!.tvBuscar.text

        RetrofitInstance.api.getPlatos("/api/json/v1/1/search.php?s=$texto")
            .enqueue(object : Callback<ListaDePlatosResponse> {
                override fun onResponse(
                    call: Call<ListaDePlatosResponse>, response: Response<ListaDePlatosResponse>
                ) {
                    if (response.body()!!.meals != null) {
                        listaPlatos = response!!.body()!!.meals
                        listaPlatosAdapter.listaPlatos = listaPlatos
                        listaPlatosAdapter.notifyDataSetChanged()
                    } else {

                        listaPlatosAdapter.listaPlatos = mutableListOf<PlatoResponse>()
                        listaPlatosAdapter.notifyDataSetChanged()
                        Toast.makeText(
                            requireContext(), " No existen platos con este nombre $texto ", Toast.LENGTH_LONG
                        ).show()
                        return
                    }
                }

                override fun onFailure(call: Call<ListaDePlatosResponse>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })

    }


}