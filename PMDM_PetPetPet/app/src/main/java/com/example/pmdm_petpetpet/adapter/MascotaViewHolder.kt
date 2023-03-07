package com.example.pmdm_petpetpet.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pmdm_petpetpet.Mascota
import com.example.pmdm_petpetpet.databinding.ItemMascotaBinding

class MascotaViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = ItemMascotaBinding.bind(view)

    fun render(mascota: Mascota){
        binding.perillo.text = mascota.nombre
        binding.rasilla.text = mascota.raza
        Glide.with(binding.fotito.context).load(mascota.imagen).into(binding.fotito)
    }
}