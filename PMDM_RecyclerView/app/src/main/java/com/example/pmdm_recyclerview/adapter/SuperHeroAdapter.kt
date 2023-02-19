package com.example.pmdm_recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pmdm_recyclerview.R
import com.example.pmdm_recyclerview.SuperHero

class SuperHeroAdapter(private val superHeroList: List<SuperHero>) : RecyclerView.Adapter<SuperHeroViewHolder>() {
    // Encargado de generar ViewHolders a medida que vayan haciendo falta
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero,parent,false))
    }
    // Al desplazar los datos arriba/abajo, dime qué dato va a ocupar el ViewHolder que ya
    // no se ve en la pantalla (para reutilizarlo) y en qué posición del RecyclerView se muestra
    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superHeroList[position]
        holder.render(item)
    }

    // Sabe el número máximo de datos que han de presentarse e informa a los ViewHolders de ello
    override fun getItemCount(): Int {
        return superHeroList.size
    }
}