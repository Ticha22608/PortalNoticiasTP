package com.semlengtp.portalnoticias

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VistaNoticiasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_noticias_elementos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val noticias = obtenerNoticias()
        val recyclerView =findViewById<RecyclerView>(R.id.recyclerNoticias)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NoticiasAdapter(noticias)

    }
        private fun obtenerNoticias(): List<Noticia> {
            return listOf(
                Noticia(
                    titulo = "Las elecciones en la Provincia de Buenos Aires ingresan en la recta final: votó más del 50% del padrón",
                    descripcion = "Los bonaerenses definen la renovación de la Legislatura y los concejos deliberantes. Los comicios determinarán el equilibrio de poder de Kicillof a nivel local y la proyección de Milei de cara a la votación nacional del mes de octubre",
                    imagen = "https://www.infobae.com/resizer/v2/QXY7U54ODRADPK7KIVP2Z6DB3A.jpg?auth=e9fceea6f0a922ce10275cae021a3dd5207a682c180e447f0a25b0d5675c562e&smart=true&width=768&height=432&quality=85"

                ),
                Noticia (
                    titulo = "Perlitas de las elecciones Buenos Aires 2025, en vivo: un fiscal detenido por robar boletas, otro que quiso abrir una urna con un cuchillo y la madre de Colapinto con el casco de su hijo",
                    descripcion = "Millones de bonaerense concurrieron hoy a las urnas para la renovación de la Legislatura provincial y de los concejos deliberantes de los 135 municipios. Los hechos curiosos de una jornada clave",
                    imagen = "https://www.infobae.com/resizer/v2/M2PYDNS3BVFFZMVJ7Z7TI3VXIA.jpg?auth=6bfd1f1f072d34ed6ad269caffbb719e10fa45adcf7843689e8345e1b999e3ce&smart=true&width=992&height=661&quality=85"
                )

            )
        }
    }
