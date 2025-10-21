package com.semlengtp.portalnoticias

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class FuentesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.green)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        setContentView(R.layout.activity_noticia_completa)

        @Suppress("DEPRECATION")
        val noticia = intent.getSerializableExtra("noticia") as? Noticia ?: return

        val titulo = findViewById<TextView>(R.id.txtTitulo)
        val categoria = findViewById<TextView>(R.id.txtCategoria)
        val fecha = findViewById<TextView>(R.id.txtFecha)
        val fuente = findViewById<TextView>(R.id.txtFuente)
        val cuerpo = findViewById<TextView>(R.id.txtCuerpo)
        val imagen = findViewById<ImageView>(R.id.imgNoticia)

        titulo.text = noticia.titulo
        categoria.text = traducirCategoria(noticia.categoria)
        fecha.text = formatearFecha(noticia.fecha)
        fuente.text = "Fuente: ${noticia.fuente}"
        cuerpo.text = noticia.cuerpo
        Picasso.get().load(noticia.imagen).into(imagen)
    }

    private fun traducirCategoria(categoria: String): String {
        val traducciones = mapOf(
            "politics" to "Política",
            "sports" to "Deportes",
            "technology" to "Tecnología",
            "business" to "Economía",
            "health" to "Salud",
            "entertainment" to "Entretenimiento"
        )
        return traducciones[categoria] ?: categoria
    }

    private fun formatearFecha(fechaOriginal: String): String {
        val formatoEntrada = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val formatoSalida = SimpleDateFormat("d 'de' MMMM 'de' yyyy", Locale("es"))
        return try {
            val fecha = formatoEntrada.parse(fechaOriginal)
            formatoSalida.format(fecha!!)
        } catch (e: Exception) {
            fechaOriginal
        }
    }
}