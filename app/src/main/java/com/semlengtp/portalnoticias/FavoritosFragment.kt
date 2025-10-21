package com.semlengtp.portalnoticias

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritosFragment : Fragment(R.layout.fragment_favoritos) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerFavoritos)
        val empty = view.findViewById<View>(R.id.textoVacio)

        val favoritos = obtenerFavoritos()

        if (favoritos.isEmpty()) {
            empty.visibility = View.VISIBLE
            recycler.visibility = View.GONE
        } else {
            empty.visibility = View.GONE
            recycler.visibility = View.VISIBLE
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = FavoritosAdapter(favoritos)
        }
    }


    private fun obtenerFavoritos(): List<NoticiaFavorita> = listOf(
        NoticiaFavorita(
            titulo = "Cómo preparar bombas de papa rellenas...",
            descripcion = "Con pocos ingredientes y un paso a paso sencillo...",
            fecha = "5 de Septiembre 2025"
        )
    )
}