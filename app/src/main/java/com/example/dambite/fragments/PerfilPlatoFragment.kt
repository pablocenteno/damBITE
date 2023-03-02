package com.example.dambite.fragments

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
        return fragmentBinding.root
    }






}