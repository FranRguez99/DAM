package com.example.pmdm_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pmdm_login.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getStringExtra("Usuario")
        val password = intent.getStringExtra("Clave")

        binding.tvUsuario.text = getString(R.string.usuario, user)
        binding.tvClave.text = getString(R.string.clave, password)

        binding.botonLogin.setOnClickListener{
            val activity = Intent(this@MainActivity2, MainActivity::class.java)
            startActivity(activity)
        }
    }
}