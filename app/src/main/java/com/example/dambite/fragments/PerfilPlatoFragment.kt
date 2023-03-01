package com.example.dambite.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.dambite.R
import com.example.dambite.databinding.FragmentListaPlatosBinding
import com.example.dambite.databinding.FragmentPerfilPlatoBinding
import com.example.dambite.rest.ListaDePlatosResponse
import com.example.dambite.rest.PlatoResponse
import com.example.dambite.rest.RetrofitInstance
import com.example.dambite.viewModel.PlatosViewModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PerfilPlatoFragment : Fragment() {

    private var binding: FragmentPerfilPlatoBinding? = null

    private var plato: PlatoResponse? = null
    private var idPlato: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentBinding = FragmentPerfilPlatoBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        arguments?.let { bundle ->
            // Recuperar el valor de la cadena del Bundle
            idPlato = bundle.getString("idPlato")
        }
        obtenerPlatos(idPlato)

        return fragmentBinding.root
    }

    fun obtenerPlatos(idPlato: String?) {

        if (idPlato == null) return
        RetrofitInstance.api.getPlatoById("/api/json/v1/1/lookup.php?i=$idPlato")
            .enqueue(object : Callback<ListaDePlatosResponse> {
                override fun onResponse(
                    call: Call<ListaDePlatosResponse>,
                    response: Response<ListaDePlatosResponse>
                ) {
                    if (response.body() != null) {
                        plato = response!!.body()!!.meals.get(0)
                        setearPerfil(plato)

                    } else {
                        return
                    }
                }

                override fun onFailure(call: Call<ListaDePlatosResponse>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })

    }


    fun setearPerfil(plato: PlatoResponse?) {

        binding!!.tvNombrePlato.text = plato?.nombre
        binding!!.textArea.text = "Area: " + plato?.area
        binding!!.textCategoriaPlato.text = "Categoria: " + plato?.categoria

        binding!!.tvDescripcion.text = "Instrucciones: " + plato?.instrucciones
        binding!!.tvTags.text = "Etiquetas: " + plato?.etiquetas
        Picasso.with(binding!!.ivPlato.context).load(plato?.urlImagen)
            .fit().centerCrop().into(binding!!.ivPlato)

    }


}