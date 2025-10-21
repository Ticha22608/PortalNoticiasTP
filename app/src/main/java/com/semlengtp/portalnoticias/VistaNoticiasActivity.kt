package com.semlengtp.portalnoticias

import android.content.Intent
import android.os.Bundle
import android.view.WindowInsetsController
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
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.semlengtp.portalnoticias.RetrofitInstancia
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Response
import android.util.Log


private lateinit var drawerLayout: DrawerLayout
private lateinit var navigationView: NavigationView
private lateinit var toolbar: Toolbar
private lateinit var toggle: ActionBarDrawerToggle
private lateinit var nombreUsuarioMenu : String

class VistaNoticiasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.statusBarColor = ContextCompat.getColor(this, R.color.green)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        setContentView(R.layout.activity_noticias_elementos)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
        /*val bundle : Bundle? = intent.extras
        if (bundle != null) {
            val usuario = bundle?.getString("USUARIO")
        }*/
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
        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerNoticias)
        cerrarSesion.setOnClickListener{
            preferencias.edit().putString(resources.getString(R.string.usuario),null).apply()
            preferencias.edit().putString(resources.getString(R.string.contraseña),null).apply()
            startActivity(Intent(this, LoginActivity::class.java))
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        navigationView.setCheckedItem(R.id.nav_noticias)
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_noticias -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, NoticiasFragment())
                        .commit()
                    supportActionBar?.title = "Portal Noticias"
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_favoritos -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, FavoritosFragment())
                        .commit()
                    supportActionBar?.title = "Mis favoritos"
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, NoticiasFragment())
                .commit()
            navigationView.setCheckedItem(R.id.nav_noticias)
            supportActionBar?.title = "Portal Noticias"
        }

        lifecycleScope.launch {
            try {
                val response = RetrofitInstancia.api.obtenerNoticias()
                if (response.isSuccessful) {
                    val noticias = response.body()?.noticias ?: emptyList()
                    Log.d("API", "Noticias recibidas: ${noticias.size}")
                    recyclerView.adapter = NoticiasAdapter(noticias)
                } else {
                    Log.e("API", "Error al obtener noticias: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Excepción: ${e.message}")
            }
        }
    }


}
