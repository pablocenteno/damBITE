package com.example.dambite.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dambite.MainActivity
import com.example.dambite.entity.Plato
import com.example.dambite.opensqlite.MiBDOpenHelper

class FavoritosViewModel : ViewModel() {

    private var dbHelper = MiBDOpenHelper(MainActivity.context, null)

    var listaPlatosFavoritos: MutableLiveData<MutableList<Plato>> = MutableLiveData()

    init {
        listaPlatosFavoritos.value = dbHelper!!.obtenerPlatos()
    }

    fun setDBHelper(miBDOpenHelper: MiBDOpenHelper) {
        dbHelper = miBDOpenHelper
    }


    fun modificarLista(listaPlatos: MutableList<Plato>) {
        listaPlatosFavoritos.value = listaPlatos
    }

    fun obtenerLista(): MutableList<Plato> {

        return listaPlatosFavoritos.value!!
    }


    fun anadirFavorito(favorito: Plato): Boolean {

        for (plato in listaPlatosFavoritos.value!!) {
            if (plato.id == favorito.id) return false
        }

        dbHelper!!.insertarPlatoFavorito(
            favorito.id, favorito.nombre, favorito.categoria, favorito.area, favorito.urlImagen
        )
        listaPlatosFavoritos?.value?.add(favorito)

        return true;

    }

    fun eliminarFavorito(favorito: Plato): Boolean {

        for (plato in listaPlatosFavoritos.value!!) {
            if (plato.id.compareTo(favorito.id) == 0) {
                dbHelper!!.eliminarPlatoFavorito(favorito.id)
                listaPlatosFavoritos?.value?.remove(favorito)

                return true
            }
        }
        return false;
    }


}