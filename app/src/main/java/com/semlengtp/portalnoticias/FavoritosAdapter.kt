package com.semlengtp.portalnoticias

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater

class FavoritosAdapter(
    private val items: List<NoticiaFavorita>
) : RecyclerView.Adapter<FavoritosAdapter.FavoritoViewHolder>() {

    class FavoritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoViewHolder {
        val vistaItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorito, parent, false)
        return FavoritoViewHolder(vistaItem)
    }

    override fun onBindViewHolder(holder: FavoritoViewHolder, position: Int) {
        val elemento = items[position]
        holder.tvTitulo.text = elemento.titulo
        holder.tvDescripcion.text = elemento.descripcion
        holder.tvFecha.text = elemento.fecha
    }

    override fun getItemCount(): Int = items.size
}