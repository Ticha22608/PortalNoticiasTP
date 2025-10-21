package com.semlengtp.portalnoticias


import android.widget.ImageButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import android.content.Intent

class FavoritosAdapter(
    private var items: MutableList<Noticia>
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
        val n = items[position]
        holder.tvTitulo.text = n.titulo
        holder.tvDescripcion.text = n.descripcion
        holder.tvFecha.text = n.fecha


        holder.itemView.setOnClickListener {
            val ctx = holder.itemView.context
            val intent = Intent(ctx, FuentesActivity::class.java)
            intent.putExtra("noticia", n)
            ctx.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = items.size


    fun replaceAll(nuevos: List<Noticia>) {
        items.clear()
        items.addAll(nuevos)
        notifyDataSetChanged()
    }
}