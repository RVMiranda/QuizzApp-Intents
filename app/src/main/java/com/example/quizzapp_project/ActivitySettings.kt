package com.example.quizzapp_project

import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivitySettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        val spinnerDificultad = findViewById<Spinner>(R.id.spinner_dificultad)
        val prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE)
        val editor = prefs.edit()


        val dificultadGuardada = prefs.getString("DIFICULTAD", "NORMAL") ?: "NORMAL"
        val index = resources.getStringArray(R.array.Dificultades).indexOf(dificultadGuardada)
        if (index >= 0) {
            spinnerDificultad.setSelection(index)
        }

        spinnerDificultad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val dificultadSeleccionada = parent?.getItemAtPosition(position).toString()
                val prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE).edit()
                prefs.putString("DIFICULTAD", dificultadSeleccionada)
                prefs.apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No es necesario hacer nada aqui
            }
        }


    }

}