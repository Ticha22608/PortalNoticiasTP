package com.semlengtp.portalnoticias
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.WindowInsetsControllerCompat



class MisFavoritosActivity : AppCompatActivity() {


    private lateinit var recyclerFavoritos: RecyclerView
    private lateinit var textoVacio: TextView

    private lateinit var drawerLayoutFav: DrawerLayout
    private lateinit var navigationViewFav: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.statusBarColor = ContextCompat.getColor(this, R.color.green)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        setContentView(R.layout.activity_mis_favoritos)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.contenedorFavoritos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        drawerLayoutFav = findViewById(R.id.drawerLayoutFavoritos)
        navigationViewFav = findViewById(R.id.navigationViewFavoritos)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Mis favoritos"

        toggle = ActionBarDrawerToggle(this, drawerLayoutFav,toolbar,R.string.nav_open,R.string.nav_close)
        drawerLayoutFav.addDrawerListener(toggle)
        toggle.syncState()

        val header = navigationViewFav.getHeaderView(0)
        val cerrarSesion = header.findViewById<ImageButton>(R.id.btnCerrarSesion)

        cerrarSesion.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            drawerLayoutFav.closeDrawer(GravityCompat.START)
        }

        navigationViewFav.setCheckedItem(R.id.nav_favoritos)

        navigationViewFav.setNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.nav_noticias -> {
                    startActivity(Intent(this, VistaNoticiasActivity::class.java))
                    drawerLayoutFav.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_favoritos -> {
                    drawerLayoutFav.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.contenedorFavoritos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        recyclerFavoritos = findViewById(R.id.recyclerFavoritos)
        textoVacio = findViewById(R.id.textoVacio)


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