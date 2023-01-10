package com.example.comocomo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comocomo.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listaPlatos = arrayListOf("Tortilla de Bacalao", "Chuletón", "Croquetas", "Pimientos", "Calamar", "Entrecot")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonSuerte.setOnClickListener{
            val platoAleat = Random.nextInt(listaPlatos.count())
            binding.queSePedira.text = listaPlatos[platoAleat]
        }

        binding.botonAgrega.setOnClickListener{
            val nuevoPlato = binding.cajanuevo.text.toString()
            listaPlatos.add(nuevoPlato)
            binding.cajanuevo.text.clear()
        }

    }
}