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
        val lista = GuardarFavoritos.getTodos(requireContext())

        if (lista.isEmpty()) {
            vacio.visibility = View.VISIBLE
            recycler.visibility = View.GONE
        } else {
            vacio.visibility = View.GONE
            recycler.visibility = View.VISIBLE

            if (adapter == null) {
                adapter = FavoritosAdapter(lista.toMutableList())
                recycler.adapter = adapter
            } else {
                adapter!!.replaceAll(lista)
            }
        }
    }
}