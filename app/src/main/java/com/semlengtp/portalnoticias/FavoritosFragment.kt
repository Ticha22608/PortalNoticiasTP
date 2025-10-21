package com.semlengtp.portalnoticias

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritosFragment : Fragment(R.layout.fragment_favoritos) {


    lateinit var recycler: RecyclerView
    lateinit var vacio: TextView
    var adapter: FavoritosAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.recyclerFavoritos)
        vacio = view.findViewById(R.id.textoVacio)

        recycler.layoutManager = LinearLayoutManager(requireContext())


        cargarFavoritos()
    }

    override fun onResume() {
        super.onResume()

        cargarFavoritos()
    }

    private fun cargarFavoritos() {

        val guardadas: List<Noticia> = GuardarFavoritos.getTodos(requireContext())

        val listaUI: List<NoticiaFavorita> = guardadas.map {
            NoticiaFavorita(
                titulo = it.titulo ?: "",
                descripcion = it.descripcion ?: "",
                fecha = it.fecha ?: ""
            )
        }

        if (listaUI.isEmpty()) {
            vacio.visibility = View.VISIBLE
            recycler.visibility = View.GONE
            adapter = null
            recycler.adapter = null
        } else {
            vacio.visibility = View.GONE
            recycler.visibility = View.VISIBLE
            if (adapter == null) {
                adapter = FavoritosAdapter(listaUI)
                recycler.adapter = adapter
            } else {

                adapter = FavoritosAdapter(listaUI)
                recycler.adapter = adapter
            }
        }
    }
}