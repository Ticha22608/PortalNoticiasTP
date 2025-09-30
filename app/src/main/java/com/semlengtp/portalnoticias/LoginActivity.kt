package com.semlengtp.portalnoticias

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    lateinit var usuario : EditText
    lateinit var contraseña : EditText
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
        usuario = findViewById(R.id.usuario)
        contraseña = findViewById(R.id.contraseña)
        recordarUsuario = findViewById(R.id.recordarUsuario)
        crearUsuario = findViewById(R.id.crearUsuario)
        iniciarSesion = findViewById(R.id.iniciarSesion)
        var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
        var usuarioGuardado = preferencias.getString(resources.getString(R.string.usuario), null)
        var contraseñaGuaradada = preferencias.getString(resources.getString(R.string.contraseña), null)

        if (usuarioGuardado != null && contraseñaGuaradada != null) actPrincipal(usuarioGuardado)
        crearUsuario.setOnClickListener {
            registrar()
        }
        iniciarSesion.setOnClickListener {
            if (usuario.text.toString() == "debug") debug()
            else login(usuario.text.toString(), contraseña.text.toString())
        }
    }

    private fun login(usuario: String, contraseña: String) {
        val datosIncompletos = inputCheck()
        if (datosIncompletos) {
            Toast.makeText(this, "Campos no completados", Toast.LENGTH_LONG).show()
        } else {
            if (recordarUsuario.isChecked) {
                var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
                preferencias.edit().putString(resources.getString(R.string.usuario), usuario).apply()
                preferencias.edit().putString(resources.getString(R.string.contraseña),contraseña).apply()
            }
            actPrincipal(usuario)
        }
    }

    private fun registrar() {
        val datosIncompletos = inputCheck()
        if (datosIncompletos) {
            Toast.makeText(this, "Campos no completados", Toast.LENGTH_SHORT).show()
        } else {
            if (recordarUsuario.isChecked) Log.i("TODO", "Traspaso de dato hacia la siguiente activity")
            val intent = Intent(this, TyC::class.java)
            Toast.makeText(this, "Por favor, leer los terminos y condiciones", Toast.LENGTH_SHORT).show()
            Log.i("TODO", "Traspaso de datos de login hacia la siguiente activity para escribir en BD si el usuario acepta TyC")
            startActivity(intent)
            finish()
        }
    }

    private fun inputCheck(): Boolean {
        return (usuario.text.toString().isEmpty() || contraseña.text.toString().isEmpty())
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

    /* Implementar luego
    fun finalizarLogin() {
        //val intent = Intent(this, VistaNoticiasActivity::class.java)
        Toast.makeText(this,"VistaNoticiasActivity aún no implementado!",Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        //mover esta función a una clase con una colección de funciones públicas luego
    }*/
}