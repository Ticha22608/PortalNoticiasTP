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



        crearUsuario.setOnClickListener {
            registrar()
        }
        iniciarSesion.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val datosIncompletos = inputCheck()
        if (datosIncompletos) {
            Toast.makeText(this, "Campos no completados", Toast.LENGTH_LONG).show()
        } else {
            if (recordarUsuario.isChecked) Log.i("TODO", "Recordar al usuario")
            val intent = Intent(this, VistaNoticiasActivity::class.java)
            startActivity(intent)
            finish()
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
        }
    }

    private fun inputCheck(): Boolean {
        return (usuario.text.toString().isEmpty() || contraseña.text.toString().isEmpty())
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