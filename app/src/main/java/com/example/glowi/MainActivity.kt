package com.example.glowi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, MContenidoActivity::class.java)
        startActivity(intent)
    }
    override fun onBackPressed() {
        // Crear y mostrar un cuadro de diálogo de confirmación
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("¿Salir?")
        alertDialogBuilder.setMessage("¿Estás seguro de que deseas salir de Glowi?")
        alertDialogBuilder.setPositiveButton("Sí") { _, _ ->
            // Si el usuario confirma, llamamos a onBackPressed para salir de la aplicación
            super.onBackPressed()
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            // Si el usuario cancela, cerramos el cuadro de diálogo sin hacer nada
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}