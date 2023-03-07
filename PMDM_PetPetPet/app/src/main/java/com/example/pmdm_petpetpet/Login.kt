package com.example.pmdm_petpetpet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pmdm_petpetpet.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar


class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = BaseDatos(this)

        binding.btnLogin.setOnClickListener {
            val usuario = binding.tfUsuario.text.toString()
            val clave = binding.tfClave.text.toString()

            if (db.checkUserAndPassword(usuario, clave)) {
                val registro = Intent(this@Login, Registro::class.java)
                registro.putExtra("Usuario", usuario)
                startActivity(registro)
                finish()
            } else {
                val snackbar = Snackbar.make(binding.root, "Usuario o contraseña incorrectos", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }


    }
}