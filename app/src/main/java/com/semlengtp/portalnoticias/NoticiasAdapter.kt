package com.semlengtp.portalnoticias

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import android.content.Intent


class NoticiasAdapter(
    private val noticias: List<Noticia>
) : RecyclerView.Adapter<NoticiasAdapter.ViewHolder>() {

    private fun traducirCategoria (categoria : String) : String {
        val traducciones = mapOf(
            "politics" to "Política",
            "sports" to "Deportes",
            "technology" to "Tecnología",
            "business" to "Economía",
            "health" to "Salud",
            "entertainment" to "Entretenimiento")
        return traducciones[categoria] ?: categoria
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_noticia, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = noticias[position]
        holder.fecha.text = noticia.fecha
        holder.categoria.text =traducirCategoria(noticia.categoria)
        holder.titulo.text = noticia.titulo
        holder.descripcion.text = noticia.descripcion
        Picasso.get().load(noticia.imagen).into(holder.imagen)

        holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context,FuentesActivity::class.java)
                intent.putExtra("noticia",noticia)
                holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = noticias.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fecha: TextView = itemView.findViewById(R.id.txtFecha)
        val categoria : TextView = itemView.findViewById(R.id.txtCategoria)
        val titulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val descripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        val imagen: ImageView = itemView.findViewById(R.id.imgNoticia)

    }
}


