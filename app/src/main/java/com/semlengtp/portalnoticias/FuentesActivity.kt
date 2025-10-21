package com.semlengtp.portalnoticias

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class FuentesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuentes)

        val titulo = intent.getStringExtra("TITULO") ?: return

        recyclerView = findViewById(R.id.recyclerFuentes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                val response = RetrofitInstancia.api.obtenerNoticias()
                if (response.isSuccessful) {
                    val todasLasNoticias = response.body()?.data ?: emptyList()
                    val fuentesRelacionadas = todasLasNoticias.filter { it.titulo.contains(titulo, ignoreCase = true) }
                    recyclerView.adapter = FuentesAdapter(fuentesRelacionadas)
                } else {
                    Log.e("FUENTES", "Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("FUENTES", "Excepción: ${e.message}")
            }
        }
    }
}

