package com.semlengtp.portalnoticias

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TyC : AppCompatActivity() {
    lateinit var btnAceptar: Button
    lateinit var btnDenegar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tyc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnAceptar = findViewById(R.id.btnAceptar)
        btnDenegar = findViewById(R.id.btnDeclinar)

        btnAceptar.setOnClickListener {
            Log.i("TODO","Guardar datos del usuario en DB")
            Log.i("TODO","Recordar o no al usuario")
            Toast.makeText(this, "Bienvenido usuario!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, VistaNoticiasActivity::class.java)
            //Toast.makeText(this,"VistaNoticiasActivity aún no implementado!",Toast.LENGTH_SHORT).show()
            //val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            //val login = LoginActivity()
            //login.finalizarLogin()
            //println("finalizarLogin() finalizó su ejecución")
            finish()
            //WIP
        }

        btnDenegar.setOnClickListener {
            Toast.makeText(this, "Para crear una cuenta, es necesario aceptar TyC", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}