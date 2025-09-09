package com.semlengtp.portalnoticias

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

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

        val btnIrFavoritos: android.widget.Button = findViewById(R.id.btnIrFavoritos)
        btnIrFavoritos.setOnClickListener {

            startActivity(android.content.Intent(this, MisFavoritosActivity::class.java))
        }
        val btnCerrarSesion: android.widget.Button = findViewById(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener {
            startActivity(android.content.Intent(this, LoginActivity::class.java))
        }


    }
        private fun obtenerNoticias(): List<Noticia> {
            return listOf(
                Noticia(
                    titulo = "Argentinos vence a Lanús por los cuartos de final de la Copa Argentina",
                    descripcion = "El Bicho y el Granate luchan por el boleto a la fase de los cuatro mejores del torneo. La Copa Argentina entró en su etapa decisiva. Esta noche, en el Cilindro de Avellaneda, Argentinos Juniors y Lanús se ven las caras por un lugar en las semifinales del certamen más federal.",
                    imagen = "https://www.infobae.com/resizer/v2/KXUQY3W66RHNJLKDU4NAG4GK4I.jpeg?auth=fad3fd1390ee08bc44138152b69e703ede62445f625992d15093b0607afd9ced&smart=true&width=420&height=236&quality=85"

                ),
                Noticia(
                    titulo = "La verdad detrás de la intervención de Gasly en el Alpine de Colapinto tras el GP de Italia de Fórmula 1",
                    descripcion = "El francés pasó al lado del coche del argentino y operó en los controles. Qué hizo y por qué su gesto terminó con una polémica en las redes sociales",
                    imagen = "https://www.infobae.com/resizer/v2/2S5AKTSRANA4RFPAGWZPYN3OI4.jpg?auth=4c75425f4f0f2199c0a851c108397e0754f68c30af3c3f129ddb8a6696ccf46b&smart=true&width=420&height=236&quality=85"
                ),
                Noticia(
                    titulo = "Dibu Martínez mostró la intimidad del asado de la Selección y el ritual de bienvenida para un juvenil antes de viajar a Ecuador ",
                    descripcion = "El arquero campeón del mundo aprovechó para subir un video de la parrilla antes de la cena y el baile que protagonizó Castelau",
                    imagen = "https://www.infobae.com/resizer/v2/IWPZ2RXDLNERTFKC2EATIUIJ5U.jpg?auth=a3e1c013df01efb3ab2ac7dacd27bcd9a79f8f6634251e1c6b705723dc9f3de0&smart=true&width=420&height=236&quality=85",
                )
            )
        }
    }
