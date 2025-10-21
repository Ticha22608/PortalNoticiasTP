package com.semlengtp.portalnoticias

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FuentesAdapter(private val fuentes: List<Noticia>) :
    RecyclerView.Adapter<FuentesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val fuente: TextView = itemView.findViewById(R.id.txtFuente)
        val descripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        val imagen: ImageView = itemView.findViewById(R.id.imgNoticia)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fuentes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = fuentes[position]
        holder.titulo.text = noticia.titulo
        holder.fuente.text = noticia.fuente
        holder.descripcion.text = noticia.descripcion
        Picasso.get().load(noticia.imagen).into(holder.imagen)
    }

    override fun getItemCount(): Int = fuentes.size
}
