package com.example.dambite.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.dambite.R
import com.example.dambite.entity.Plato
import com.example.dambite.rest.PlatoResponse
import com.example.dambite.viewModel.FavoritosViewModel
import com.squareup.picasso.Picasso

class FavoritosRVAdapter (


    ):RecyclerView.Adapter<FavoritoViewHolder>()
    {
        private lateinit var context: Context
        private lateinit var favoritos: MutableList<Plato>
        fun FavoritosRVAdapter(context: Context, favoritos: MutableList<Plato>)
        {

            this.context = context
            this.favoritos=favoritos

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return FavoritoViewHolder(layoutInflater.inflate(R.layout.favorito_rvitem, parent, false))
        }

        override fun onBindViewHolder(holder: FavoritoViewHolder, position: Int) {

            holder.render(favoritos[position])
        }

        override fun getItemCount(): Int {
            return favoritos.size
        }

    }


    class FavoritoViewHolder(view: View
    ) : RecyclerView.ViewHolder(view) {
        var textViewNombre :TextView= itemView.findViewById(R.id.tvnombrePlato)
        var textViewFoto :ImageView= itemView.findViewById(R.id.imgVPlato)


        fun render(plato: Plato

        ) {
                textViewNombre.text=plato.nombre
            Picasso.get().load(plato.urlImagen)
                .fit().centerCrop().into(textViewFoto)

        }





    }
