package com.example.sqlite.b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqlite.databinding.ActivityMain2Binding
import com.example.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
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
            Toast.makeText(this, name + " datos insertados", Toast.LENGTH_LONG).show()
            binding.enterId.text.clear()
            binding.enterName.text.clear()
            binding.enterAge.text.clear()
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
        }
        binding.delete.setOnClickListener {
            val db = DBHelper(this, null)
            db.deleteData(binding.enterId.text.toString().toInt())
            Toast.makeText(this, "Dato borrado de la base de datos", Toast.LENGTH_LONG).show()
        }
        // below code is to add on  click
        // listener to our print name button
        binding.printName.setOnClickListener {
            binding.codigo.text = ""
            binding.Name.text = ""
            binding.Age.text = ""

            val db = DBHelper(this, null)
            val cursor = db.getName()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            binding.codigo.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ID_COL)) + "\n")
            binding.Name.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NAME_COl)) + "\n")
            binding.Age.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.AGE_COL)) + "\n")

            // moving our cursor to next
            // position and appending values
            while (cursor.moveToNext()) {
                binding.codigo.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ID_COL)) + "\n")
                binding.Name.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NAME_COl)) + "\n")
                binding.Age.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.AGE_COL)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
    }
}