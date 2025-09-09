package com.semlengtp.portalnoticias

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NoticiasAdapter(
    private val noticias: List<Noticia>
) : RecyclerView.Adapter<NoticiasAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_noticia, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = noticias[position]
        holder.titulo.text = noticia.titulo
        holder.descripcion.text = noticia.descripcion
        Picasso.get().load(noticia.imagen).into(holder.imagen)
    }

    override fun getItemCount(): Int = noticias.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val descripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        val imagen: ImageView = itemView.findViewById(R.id.imgNoticia)
    }
}


