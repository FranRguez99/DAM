package com.example.pmdm_kemekomo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.example.pmdm_kemekomo2.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listaPlatos = arrayListOf("Tortilla de Bacalao", "Chuletón", "Croquetas", "Pimientos", "Calamar", "Entrecot")
    private var limite = 5
    private var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.boton.setOnClickListener{
            if (contador < limite){
                val platoAleat = Random.nextInt(listaPlatos.count())
                binding.plato.text = listaPlatos[platoAleat]
                contador++
            } else {
                binding.boton.isEnabled = false
            }

        }

        binding.cantidad.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                limite = 5 + progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                contador = 0
                binding.plato.text = ""
                binding.boton.isEnabled = true
                Toast.makeText(applicationContext, "El tope es: $limite", Toast.LENGTH_SHORT).show()
            }
        })

    }
}