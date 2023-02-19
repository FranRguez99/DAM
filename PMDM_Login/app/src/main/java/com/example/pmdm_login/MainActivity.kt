package com.example.pmdm_login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pmdm_login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonLogin.setOnClickListener{
            val usuario = binding.tfUsuario.text.toString()
            val contr = binding.tfContraseA.text.toString()

            binding.tfUsuario.text?.clear()
            binding.tfContraseA.text?.clear()
            binding.tfUsuario.requestFocus()

            //Toast.makeText(applicationContext, "Usuario: $usuario y Contraseña: $contr", Toast.LENGTH_SHORT).show()
            val activity2 = Intent(this@MainActivity, MainActivity2::class.java)
            activity2.putExtra("Usuario", usuario)
            activity2.putExtra("Clave", contr)
            startActivity(activity2)
        }

    }
}