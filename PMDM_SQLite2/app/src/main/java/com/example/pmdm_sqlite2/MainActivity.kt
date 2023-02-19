package com.example.pmdm_sqlite2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pmdm_sqlite2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // below code is to add on click
        // listener to our add name button
        binding.addName.setOnClickListener {
            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = DBHelper(this, null)

            // creating variables for values
            // in name and age edit texts
            val name = binding.enterName.text.toString()
            val age = binding.enterAge.text.toString()

            // calling method to add
            // name to our database
            db.addName(name, age)

            // Toast to message on the screen
            Toast.makeText(this, name + " ha sido insertado en la bbdd", Toast.LENGTH_LONG).show()
            binding.enterId.text.clear()
            binding.enterName.text.clear()
            binding.enterAge.text.clear()
            showData()
        }
        binding.update.setOnClickListener {
            val db = DBHelper(this, null)
            db.updateData(
                binding.enterId.text.toString().toInt(),
                binding.enterName.text.toString(),
                binding.enterAge.text.toString()
            )
            Toast.makeText(this, binding.enterName.text.toString() + " Datos actualizados", Toast.LENGTH_LONG).show()

            binding.enterId.text.clear()
            binding.enterName.text.clear()
            binding.enterAge.text.clear()
            showData()
        }
        binding.delete.setOnClickListener {
            val db = DBHelper(this, null)
            db.deleteData(binding.enterId.text.toString().toInt())
            Toast.makeText(this, "Dato borrado de la base de datos", Toast.LENGTH_LONG).show()
            showData();
        }

    }

    private fun showData() {
        binding.codigo.text = ""
        binding.Name.text = ""
        binding.Age.text = ""

        val db = DBHelper(this, null)
        val cursor = db.getName()

        cursor?.apply {
            moveToFirst()
            binding.codigo.append(getString(getColumnIndexOrThrow(DBHelper.ID_COL)) + "\n")
            binding.Name.append(getString(getColumnIndexOrThrow(DBHelper.NAME_COl)) + "\n")
            binding.Age.append(getString(getColumnIndexOrThrow(DBHelper.AGE_COL)) + "\n")

            while (moveToNext()) {
                binding.codigo.append(getString(getColumnIndexOrThrow(DBHelper.ID_COL)) + "\n")
                binding.Name.append(getString(getColumnIndexOrThrow(DBHelper.NAME_COl)) + "\n")
                binding.Age.append(getString(getColumnIndexOrThrow(DBHelper.AGE_COL)) + "\n")
            }

            close()
        }
    }
}