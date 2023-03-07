package com.example.pmdm_petpetpet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pmdm_petpetpet.databinding.ActivityRegistroBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class Registro : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding

    val fotoPorDefecto = "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/65761296352685.5eac4787a4720.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bd = BaseDatos(this)

        val usuario = intent.getStringExtra("Usuario")

        binding.tvUsuario.text = "Usuario: " + usuario

        if(bd.getAllMascotas().isEmpty()) {
            val mascotas = listOf(
                Mascota(1, "Luna", "https://bowwowinsurance.com.au/wp-content/uploads/2020/09/shutterstock_15250126-Alaskan-Husky-in-front-of-white-background-thumbnail.jpg", "Husky", "Femenino", SimpleDateFormat("dd/MM/yyyy", Locale.US).parse("01/01/2010"), "12345678A"),
                Mascota(2, "Bob", "https://bowwowinsurance.com.au/wp-content/uploads/2018/10/american-staffordshire-terrier-amstaff-american-staffy-700x700.jpg", "Staffordshire Terrier", "Masculino", SimpleDateFormat("dd/MM/yyyy", Locale.US).parse("02/02/2011"), "23456789B"),
                Mascota(3, "Buddy", "https://bowwowinsurance.com.au/wp-content/uploads/2018/10/beagle-700x700.jpg", "Beagle", "Masculino", SimpleDateFormat("dd/MM/yyyy", Locale.US).parse("03/03/2012"), "34567890C"),
                Mascota(4, "Papa", "https://bowwowinsurance.com.au/wp-content/uploads/2018/10/dalmatian-700x700.jpg", "Dálmata", "Femenino", SimpleDateFormat("dd/MM/yyyy", Locale.US).parse("03/03/2012"), "34567890C"),
            )

            for (mascota in mascotas) {
                bd.addMascota(mascota)
            }
        }

        // Botón añadir a la BBDD
        binding.btnAlta.setOnClickListener {
            val db = BaseDatos(this)

            val formato = SimpleDateFormat("dd-MM-yyyy")
            val mascota = formato.parse(binding.tfFechaNac.text.toString())?.let {
                Mascota(
                    id = binding.tfCodigo.text.toString().toInt(),
                    nombre = binding.tfNombre.text.toString(),
                    imagen = fotoPorDefecto,
                    raza = binding.tfRaza.text.toString(),
                    sexo = binding.tfSexo.text.toString(),
                    fechaNacimiento = it,
                    dni = binding.tfDNI.text.toString()
                )
            }

            if (mascota != null) {
                db.addMascota(mascota)
                val snackbar = Snackbar.make(binding.root, "Mascota insertada", Snackbar.LENGTH_LONG)
                snackbar.show()
            }

            binding.tfCodigo.text.clear()
            binding.tfNombre.text.clear()
            binding.tfRaza.text.clear()
            binding.tfSexo.text.clear()
            binding.tfFechaNac.text.clear()
            binding.tfDNI.text.clear()
        }

        // Botón modificar
        binding.btnModifica.setOnClickListener {
            val db = BaseDatos(this)

            val formato = SimpleDateFormat("dd-MM-yyyy")
            val mascota = formato.parse(binding.tfFechaNac.text.toString())?.let {
                Mascota(
                    id = binding.tfCodigo.text.toString().toInt(),
                    nombre = binding.tfNombre.text.toString(),
                    imagen = fotoPorDefecto,
                    raza = binding.tfRaza.text.toString(),
                    sexo = binding.tfSexo.text.toString(),
                    fechaNacimiento = it,
                    dni = binding.tfDNI.text.toString()
                )
            }

            if (mascota != null) {
                db.updateMascota(mascota)
                val snackbar = Snackbar.make(binding.root, "Mascota modificada", Snackbar.LENGTH_LONG)
                snackbar.show()
            }

            binding.tfCodigo.text.clear()
            binding.tfNombre.text.clear()
            binding.tfRaza.text.clear()
            binding.tfSexo.text.clear()
            binding.tfFechaNac.text.clear()
            binding.tfDNI.text.clear()
        }

        // Boton borrar
        binding.btnBorrar.setOnClickListener {
            val db = BaseDatos(this)
            val id = binding.tfCodigo.text.toString().toInt()

            db.deleteMascota(id)

            val snackbar = Snackbar.make(binding.root, "Mascota borrada", Snackbar.LENGTH_LONG)
            snackbar.show()

            binding.tfCodigo.text.clear()
            binding.tfNombre.text.clear()
            binding.tfRaza.text.clear()
            binding.tfSexo.text.clear()
            binding.tfFechaNac.text.clear()
            binding.tfDNI.text.clear()

        }

        // Boton consulta
        binding.btnConsulta.setOnClickListener {
            val db = BaseDatos(this)
            val id = binding.tfCodigo.text.toString().toInt()

            val mascota = db.getMascota(id)

            binding.tfNombre.setText(mascota?.nombre)
            binding.tfRaza.setText(mascota?.raza)
            binding.tfSexo.setText(mascota?.sexo)
            binding.tfFechaNac.setText(SimpleDateFormat("dd-MM-yyyy").format(mascota?.fechaNacimiento))
            binding.tfDNI.setText(mascota?.dni)

        }

        // Boton consulta todas
        binding.btnConsultaTodas.setOnClickListener {
            val lista = Intent(this@Registro, Lista::class.java)
            startActivity(lista)
        }

    }
}