package com.semlengtp.portalnoticias

import android.widget.Toast
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers




class NoticiasFragment : Fragment(R.layout.fragment_noticias) {

    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val vista = inflater.inflate(R.layout.fragment_noticias, container, false)
        recycler = vista.findViewById(R.id.recyclerNoticias)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = NoticiasAdapter(emptyList()) // arranca vacío
        cargarNoticias()
        return vista
        return vista
    }


    private fun cargarNoticias() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val resp = withContext(Dispatchers.IO) {
                    RetrofitInstancia.api.obtenerNoticias(
                        idioma = "es",
                        pais = "ar",
                        cantidad = 10
                    )
                }
                if (resp.isSuccessful) {
                    val lista = resp.body()?.noticias ?: emptyList()
                    recycler.adapter = NoticiasAdapter(lista)
                } else {
                    Toast.makeText(requireContext(),
                        "Error ${resp.code()}: ${resp.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {

            }
        }
    }
}




