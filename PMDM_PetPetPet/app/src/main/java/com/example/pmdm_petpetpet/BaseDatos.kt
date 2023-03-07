package com.example.pmdm_petpetpet

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class BaseDatos(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "PetPetPet"

        // Mascota table
        private const val TABLE_MASCOTA = "mascota"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_IMAGEN = "imagen"
        private const val COLUMN_RAZA = "raza"
        private const val COLUMN_SEXO = "sexo"
        private const val COLUMN_FECHA_NACIMIENTO = "fecha_nacimiento"
        private const val COLUMN_DNI = "dni"

        // Usuario table
        private const val TABLE_USUARIO = "usuario"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Crear tabla mascotas
        db?.execSQL(
            "CREATE TABLE $TABLE_MASCOTA (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_NOMBRE TEXT," +
                    "$COLUMN_IMAGEN TEXT," +
                    "$COLUMN_RAZA TEXT," +
                    "$COLUMN_SEXO TEXT," +
                    "$COLUMN_FECHA_NACIMIENTO DATE," +
                    "$COLUMN_DNI TEXT)"
        )

        // Crear tabla usuarios
        db?.execSQL(
            "CREATE TABLE $TABLE_USUARIO (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_USERNAME TEXT," +
                    "$COLUMN_PASSWORD TEXT)"
        )

        // Insertar usuario predeterminado
        db?.execSQL(
            "INSERT INTO $TABLE_USUARIO (" +
                    "$COLUMN_USERNAME," +
                    "$COLUMN_PASSWORD) VALUES ('fran','1234')"
        )

        // Pre-insert a list of mascotas

    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drop the existing tables and recreate them
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MASCOTA")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIO")
        onCreate(db)
    }


    fun addMascota(mascota: Mascota) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_ID, mascota.id)
        values.put(COLUMN_NOMBRE, mascota.nombre)
        values.put(COLUMN_IMAGEN, mascota.imagen)
        values.put(COLUMN_RAZA, mascota.raza)
        values.put(COLUMN_SEXO, mascota.sexo)
        values.put(COLUMN_FECHA_NACIMIENTO, mascota.fechaNacimiento.time)
        values.put(COLUMN_DNI, mascota.dni)
        db.insert(TABLE_MASCOTA, null, values)
        db.close()
    }



    fun updateMascota(mascota: Mascota) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, mascota.nombre)
            put(COLUMN_IMAGEN, mascota.imagen)
            put(COLUMN_RAZA, mascota.raza)
            put(COLUMN_SEXO, mascota.sexo)
            put(COLUMN_FECHA_NACIMIENTO, mascota.fechaNacimiento.time)
            put(COLUMN_DNI, mascota.dni)
        }
        db.update(TABLE_MASCOTA, values, "id = ?", arrayOf(mascota.id.toString()))
        db.close()
    }

    fun deleteMascota(id: Int): Boolean {
        val db = writableDatabase
        return db.delete(TABLE_MASCOTA, "$COLUMN_ID = $id", null) > 0
    }

    @SuppressLint("Range")
    fun getMascota(id: Int): Mascota? {
        val db = this.readableDatabase
        var mascota: Mascota? = null
        val cursor = db.query(
            TABLE_MASCOTA, arrayOf(
                COLUMN_ID,
                COLUMN_NOMBRE,
                COLUMN_IMAGEN,
                COLUMN_RAZA,
                COLUMN_SEXO,
                COLUMN_FECHA_NACIMIENTO,
                COLUMN_DNI
            ), "$COLUMN_ID=?", arrayOf(id.toString()), null, null, null, null
        )
        if (cursor.moveToFirst()) {
            val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
            val imagen = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEN))
            val raza = cursor.getString(cursor.getColumnIndex(COLUMN_RAZA))
            val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
            val fechaNacimiento =
                Date(cursor.getLong(cursor.getColumnIndex(COLUMN_FECHA_NACIMIENTO)))
            val dni = cursor.getString(cursor.getColumnIndex(COLUMN_DNI))
            mascota = Mascota(id, nombre, imagen, raza, sexo, fechaNacimiento, dni)
        }
        cursor.close()
        db.close()
        return mascota
    }

    @SuppressLint("Range")
    fun getAllMascotas(): List<Mascota> {
        val mascotas = mutableListOf<Mascota>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_MASCOTA"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val imagen = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEN))
                val raza = cursor.getString(cursor.getColumnIndex(COLUMN_RAZA))
                val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                val fechaNacimiento = Date(cursor.getLong(cursor.getColumnIndex(COLUMN_FECHA_NACIMIENTO)))
                val dni = cursor.getString(cursor.getColumnIndex(COLUMN_DNI))
                val mascota = Mascota(id, nombre, imagen, raza, sexo, fechaNacimiento, dni)
                mascotas.add(mascota)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return mascotas
    }

    fun checkUserAndPassword(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val selectionArgs = arrayOf(username, password)
        val query = "SELECT * FROM $TABLE_USUARIO WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val cursor = db.rawQuery(query, selectionArgs)
        val count = cursor.count
        cursor.close()
        return count > 0
    }


}
