package com.example.glowi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class Menu_Activity : AppCompatActivity() {
    //Añadimos la lista en menu list del contenido y sunbcontenido
    private lateinit var menuListView: ListView
    private val menuOptions = arrayOf(
        "Base Técnica" to arrayOf(
            SubOption("Clase 1", Catedra1_Activity::class.java)
        )

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Logica del ListView
        menuListView = findViewById(R.id.menuListView)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menuOptions.map { it.first })
        menuListView.adapter = adapter

        menuListView.setOnItemClickListener { _, _, position, _ ->
            val selectedOption = menuOptions[position]
            val subOptions = selectedOption.second
            if (subOptions.isNotEmpty()) {
                val subOptionsNames = subOptions.map { it.name }.toTypedArray()
                val subOptionsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, subOptionsNames)
                menuListView.adapter = subOptionsAdapter

                menuListView.setOnItemClickListener { _, _, subPosition, _ ->
                    val selectedSubOption = subOptions[subPosition]
                    startActivity(Intent(this, selectedSubOption.activityClass))
                }
            } else {
                startActivity(Intent(this, selectedOption.second::class.java))
            }
    }
  }

}

data class SubOption(val name: String, val activityClass: Class<out AppCompatActivity>)