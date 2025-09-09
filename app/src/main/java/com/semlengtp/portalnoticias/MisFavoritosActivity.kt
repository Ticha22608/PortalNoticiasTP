package com.semlengtp.portalnoticias
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class MisFavoritosActivity : AppCompatActivity() {


    private lateinit var recyclerFavoritos: RecyclerView
    private lateinit var textoVacio: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_favoritos)
        recyclerFavoritos = findViewById(R.id.recyclerFavoritos)
        textoVacio = findViewById(R.id.textoVacio)
        val btnVolver: android.widget.Button = findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }


        val favoritos: List<com.semlengtp.portalnoticias.NoticiaFavorita> = listOf(
            NoticiaFavorita(
                titulo = "Cómo es el truco casero para limpiar el microondas con una esponja: queda impecable y desinfectado",
                descripcion = "La esponja de cocina, ese objeto cotidiano que solemos usar para lavar platos, también puede convertirse en la clave para limpiar a fondo el microondas. Con un procedimiento muy simple, permite desprender la grasa acumulada y desinfectar el interior del electrodoméstico sin necesidad de productos químicos.",
                fecha = "26 de Agosto 2025"
            ),
            NoticiaFavorita(
                titulo = "Cómo preparar bombas de papa rellenas y llevarte todos los aplausos",
                descripcion = "Con pocos ingredientes y un paso a paso sencillo, esta preparación se convierte en la opción ideal para compartir en reuniones, acompañar un plato principal o disfrutar en cualquier momento del día.",
                fecha = "5 de Septiembre 2025"
            ),
            NoticiaFavorita(
                titulo = "De choripanes a pastelitos: esto comieron los bonaerenses después de votar en las elecciones 2025",
                descripcion = "Tras asistir a las urnas, miles de vecinos aprovecharon la jornada para recorrer distintos locales y disfrutar de un recorrido gastronómico que incluyó desde opciones de desayuno hasta platos bien argentinos para el almuerzo.",
                fecha = "7 de Septiembre 2025"
            )
        )

        if (favoritos.isEmpty()) {
            textoVacio.visibility = View.VISIBLE
            recyclerFavoritos.visibility = View.GONE
        } else {
            textoVacio.visibility = View.GONE
            recyclerFavoritos.visibility = View.VISIBLE

            recyclerFavoritos.layoutManager = LinearLayoutManager(this)

            recyclerFavoritos.adapter = FavoritosAdapter(favoritos)
        }
    }
}

