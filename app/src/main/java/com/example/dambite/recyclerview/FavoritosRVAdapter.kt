package com.example.dambite.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dambite.R
import com.example.dambite.entity.Plato
import com.example.dambite.rest.PlatoResponse
import com.squareup.picasso.Picasso

class FavoritosRVAdapter (

    var listaPlatos: MutableList<Plato>,
    private val perfilPlato: (Plato) -> Unit,
    private val anadirFavorito: (Plato) -> Unit
    ):RecyclerView.Adapter<FavoritoViewHolder>()
    {

        //Definimos su metedo create estableciendo su layout
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return FavoritoViewHolder(layoutInflater.inflate(R.layout.favorito_rvitem, parent, false))
        }

        //Renederizamos cada elemento de la lista
        override fun onBindViewHolder(holder: FavoritoViewHolder, position: Int) {
            val item = listaPlatos[position]
            holder.render(item,perfilPlato,anadirFavorito)
        }

        //Obtenemos el tamaño de la lista
        override fun getItemCount(): Int {
            return listaPlatos.size

        }
    }


    class FavoritoViewHolder(view: View
    ) : RecyclerView.ViewHolder(view) {

        val imageView = view.findViewById<ImageView>(R.id.imgVPlato)
        val textView = view.findViewById<TextView>(R.id.tvnombrePlato)
        val btnFavorito = view.findViewById<Button>(R.id.btnFavorito)
        fun render(
            plato: Plato,
            onClickPerfil: (Plato) -> Unit,
            onClickFavorito: (Plato) -> Unit
        ) {
            itemView.setOnClickListener { onClickPerfil(plato) }
            btnFavorito.setOnClickListener { onClickFavorito(plato) }

            textView.text = plato.nombre

            Picasso.with(imageView.context).load(plato.urlImagen)
                .fit().centerCrop().into(imageView)


        }





    }
