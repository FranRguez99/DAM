package com.example.pmdm_petpetpet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pmdm_petpetpet.adapter.MascotaAdapter
import com.example.pmdm_petpetpet.databinding.ActivityListaBinding

class Lista : AppCompatActivity() {

    private lateinit var binding: ActivityListaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVolver.setOnClickListener {
            val registro = Intent(this@Lista, Registro::class.java)
            startActivity(registro)
        }

        initRecyclerView()
    }


    private fun initRecyclerView() {
        val db = BaseDatos(this)

        binding.recyclerMascotas.layoutManager = LinearLayoutManager(this)
        binding.recyclerMascotas.adapter = MascotaAdapter(db.getAllMascotas())
    }
}