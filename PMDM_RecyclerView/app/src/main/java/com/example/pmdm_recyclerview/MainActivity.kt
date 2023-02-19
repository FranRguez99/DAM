package com.example.pmdm_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pmdm_recyclerview.adapter.SuperHeroAdapter
import com.example.pmdm_recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enlaza este código con la vista principal (activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Enlazaría (si activo) este código con la vista principal
        //setContentView(R.layout.activity_main)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        // Se le indica el tipo espaciado a utilizar (lista de una columna))
        binding.recyclerSuperHero.layoutManager = LinearLayoutManager(this)
        // Se le indica qué clase Adapter se encarga de proveer los datos al RecyclerView
        binding.recyclerSuperHero.adapter = SuperHeroAdapter(SuperHeroProvider.superHeroList)

        // (Si activo) enlazaría este código con el componente RecyclerView de la vista principal
        // val recyclerView = findViewById<RecyclerView>(R.id.recyclerSuperHero)
        // recyclerView.layoutManager = LinearLayoutManager(this)
        // recyclerView.adapter = SuperHeroAdapter(SuperHeroProvider.superHeroList)
    }
}