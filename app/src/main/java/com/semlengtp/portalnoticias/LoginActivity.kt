package com.semlengtp.portalnoticias

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.toString

class LoginActivity : AppCompatActivity() {
    lateinit var usuarioNombre : EditText
    lateinit var usuarioContraseña : EditText
    lateinit var recordarUsuario : CheckBox
    lateinit var crearUsuario : Button
    lateinit var iniciarSesion : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        usuarioNombre = findViewById(R.id.usuario)
        usuarioContraseña = findViewById(R.id.contraseña)
        recordarUsuario = findViewById(R.id.recordarUsuario)
        crearUsuario = findViewById(R.id.crearUsuario)
        iniciarSesion = findViewById(R.id.iniciarSesion)
        var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
        var usuarioGuardado = preferencias.getString(resources.getString(R.string.usuario), null)
        var contraseñaGuaradada = preferencias.getString(resources.getString(R.string.contraseña), null)

        if (usuarioGuardado != null && contraseñaGuaradada != null) actPrincipal(usuarioGuardado)
        crearUsuario.setOnClickListener {
            registrar(usuarioNombre.text.toString(), usuarioContraseña.text.toString())
        }
        iniciarSesion.setOnClickListener {
            if (usuarioNombre.text.toString() == "debug") debug()
            else login(usuarioNombre.text.toString(), usuarioContraseña.text.toString())
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
    }

    private fun login(usuario: String, contraseña: String) {
        val datosIncompletos = inputCheck()
        if (datosIncompletos) {
            Toast.makeText(this, "Campos no completados", Toast.LENGTH_SHORT).show()
        } else {
            val usuarioEncontrado = AppDatabase.getDatabase(applicationContext).usuarioDao().encontrarExacto(usuario, contraseña)
            if (usuarioEncontrado == null) {
                Toast.makeText(this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                return
            }
            if (recordarUsuario.isChecked) {
                var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
                preferencias.edit().putString(resources.getString(R.string.usuario), usuario).apply()
                preferencias.edit().putString(resources.getString(R.string.contraseña),contraseña).apply()
            }
            actPrincipal(usuario)
        }
    }

    private fun registrar(usuario: String, contraseña: String) {
        val datosIncompletos = inputCheck()
        if (datosIncompletos) {
            Toast.makeText(this, "Campos no completados", Toast.LENGTH_SHORT).show()
        } else {
            val usuarioEncontrado = AppDatabase.getDatabase(applicationContext).usuarioDao().encontrarNombre(usuario)
            if (usuarioEncontrado != null) {
                Toast.makeText(this, "Usuario ya creado!", Toast.LENGTH_SHORT).show()
                return
            }
            val intent = Intent(this, TyC::class.java)
            Toast.makeText(this, "Por favor, leer los terminos y condiciones", Toast.LENGTH_SHORT).show()
            intent.putExtra("USUARIO", usuario)
            intent.putExtra("CONTRASEÑA", contraseña)
            startActivity(intent)
            finish()
        }
    }

    private fun inputCheck(): Boolean {
        return (usuarioNombre.text.toString().isEmpty() || usuarioContraseña.text.toString().isEmpty())
    }

    private fun debug() {
        val intent = Intent(this, testactivity::class.java)
        Toast.makeText(this,"Debug",Toast.LENGTH_SHORT).show()
        startActivity(intent)
        finish()
    }

    private fun actPrincipal(usuario: String) {
        val intent = Intent(this, VistaNoticiasActivity::class.java)
        intent.putExtra("USUARIO", usuario)
        startActivity(intent)
        finish()
    }
}