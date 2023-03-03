package com.example.dambite.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.dambite.databinding.FragmentPerfilPlatoBinding
import com.example.dambite.rest.ListaDePlatosResponse
import com.example.dambite.rest.PlatoResponse
import com.example.dambite.rest.RetrofitInstance
import com.example.dambite.viewModel.FavoritosViewModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.file.Paths.get


class PerfilPlatoFragment : Fragment() {

    private var binding: FragmentPerfilPlatoBinding? = null

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
        mostrarDatos()
        return fragmentBinding.root
    }

    fun mostrarDatos(){
        RetrofitInstance.api.getUnPlato("/api/json/v1/1/lookup.php?i=$idPlato").enqueue(object:Callback<ListaDePlatosResponse>{
            override fun onResponse(
                call: Call<ListaDePlatosResponse>,
                response: Response<ListaDePlatosResponse>
            ) {
                if(response.body()!=null){

                    binding?.textCategoriaPlato!!.text= response.body()!!.meals.get(0).categoria
                    Picasso.get().load(response.body()!!.meals.get(0).urlImagen).into(binding!!.ivPlato)
                    binding?.textArea!!.text= response.body()!!.meals.get(0).area
                    binding?.tvDescripcion!!.text= response.body()!!.meals.get(0).instrucciones
                    binding?.tvTags!!.text= response.body()!!.meals.get(0).etiquetas


                }
            }

            override fun onFailure(call: Call<ListaDePlatosResponse>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }


    })

}





}