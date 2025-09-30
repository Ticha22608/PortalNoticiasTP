package com.semlengtp.portalnoticias
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import android.widget.ImageButton



private lateinit var drawerLayout: DrawerLayout
private lateinit var navigationView: NavigationView
private lateinit var toolbar: Toolbar
private lateinit var toggle: ActionBarDrawerToggle
class VistaNoticiasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_noticias_elementos)

        toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)


        setSupportActionBar(toolbar)
        supportActionBar?.title = "Portal Noticias"

        toggle = ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.nav_open,R.string.nav_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val header = navigationView.getHeaderView(0)
        val cerrarSesion = header.findViewById<ImageButton>(R.id.btnCerrarSesion)

        cerrarSesion.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        navigationView.setCheckedItem(R.id.nav_noticias)


        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_noticias -> {

                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_favoritos -> {

                    startActivity(Intent(this, MisFavoritosActivity::class.java))
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }


                else -> false
            }
        }
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
                    fecha = "8 de Septiembre de 2025",
                    titulo = "Argentinos vence a Lanús por los cuartos de final de la Copa Argentina",
                    descripcion = "El Bicho y el Granate luchan por el boleto a la fase de los cuatro mejores del torneo. La Copa Argentina entró en su etapa decisiva. Esta noche, en el Cilindro de Avellaneda, Argentinos Juniors y Lanús se ven las caras por un lugar en las semifinales del certamen más federal.",
                    imagen = "https://www.infobae.com/resizer/v2/KXUQY3W66RHNJLKDU4NAG4GK4I.jpeg?auth=fad3fd1390ee08bc44138152b69e703ede62445f625992d15093b0607afd9ced&smart=true&width=420&height=236&quality=85"

                ),
                Noticia(
                    fecha = "8 de Septiembre de 2025",
                    titulo = "La verdad detrás de la intervención de Gasly en el Alpine de Colapinto tras el GP de Italia de Fórmula 1",
                    descripcion = "El francés pasó al lado del coche del argentino y operó en los controles. Qué hizo y por qué su gesto terminó con una polémica en las redes sociales",
                    imagen = "https://www.infobae.com/resizer/v2/2S5AKTSRANA4RFPAGWZPYN3OI4.jpg?auth=4c75425f4f0f2199c0a851c108397e0754f68c30af3c3f129ddb8a6696ccf46b&smart=true&width=420&height=236&quality=85"
                ),
                Noticia(
                    fecha = "8 de Septiembre de 2025",
                    titulo = "Dibu Martínez mostró la intimidad del asado de la Selección y el ritual de bienvenida para un juvenil antes de viajar a Ecuador ",
                    descripcion = "El arquero campeón del mundo aprovechó para subir un video de la parrilla antes de la cena y el baile que protagonizó Castelau",
                    imagen = "https://www.infobae.com/resizer/v2/IWPZ2RXDLNERTFKC2EATIUIJ5U.jpg?auth=a3e1c013df01efb3ab2ac7dacd27bcd9a79f8f6634251e1c6b705723dc9f3de0&smart=true&width=420&height=236&quality=85",
                )
            )
        }

    }
