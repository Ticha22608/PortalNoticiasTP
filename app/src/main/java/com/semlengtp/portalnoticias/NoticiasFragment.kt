package com.semlengtp.portalnoticias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoticiasFragment : Fragment(R.layout.fragment_noticias) {

    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val vista = inflater.inflate(R.layout.fragment_noticias, container, false)
        recycler = vista.findViewById(R.id.recyclerNoticias)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        // CARGA LOCAL (mock) — sin API
        val noticias = obtenerNoticias()
        recycler.adapter = NoticiasAdapter(noticias)

        return vista
    }


    private fun obtenerNoticias(): List<Noticia> = listOf(
        Noticia(
            fecha = "8 de Septiembre de 2025, 10:15",
            titulo = "Argentinos venció a Lanús y está en semifinales",
            descripcion = "El Bicho se metió entre los cuatro mejores de la Copa Argentina.",
            imagen = "https://picsum.photos/seed/a1/600/400"
        ),
        Noticia(
            fecha = "8 de Septiembre de 2025, 11:42",
            titulo = "Colapinto y el análisis del GP de Italia",
            descripcion = "Claves del rendimiento y la estrategia para el sprint final del campeonato.",
            imagen = "https://picsum.photos/seed/a2/600/400"
        ),
        Noticia(
            fecha = "8 de Septiembre de 2025, 13:05",
            titulo = "Selección: asado y bienvenida a los juveniles",
            descripcion = "El plantel compartió un asado y hubo bautismo de un convocado.",
            imagen = "https://picsum.photos/seed/a3/600/400"
        )
    )
}