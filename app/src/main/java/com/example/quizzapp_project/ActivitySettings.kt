package com.example.quizzapp_project

import android.os.Bundle
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.android.material.slider.Slider
import android.util.Log


class ActivitySettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val spinnerDificultad = findViewById<Spinner>(R.id.spinner_dificultad)
        val sliderPistas = findViewById<Slider>(R.id.slider_pistas)
        val prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE)
        val editor = prefs.edit()

        // ✅ Recuperar valores guardados o asignar valores predeterminados
        val dificultadGuardada = prefs.getString("DIFICULTAD", "NORMAL") ?: "NORMAL"
        val numHintsGuardado = prefs.getInt("NUM_HINTS", 0)
        Log.d("ActivitySettings", "Valor guardado de NUM_HINTS: $numHintsGuardado")

        // ✅ Configurar el spinner según la dificultad guardada
        val index = resources.getStringArray(R.array.Dificultades).indexOf(dificultadGuardada)
        if (index >= 0) {
            spinnerDificultad.setSelection(index)
        }

        // ✅ Establecer el valor del slider con las pistas guardadas
        sliderPistas.value = numHintsGuardado.toFloat()
        Log.d("ActivitySettings", "Slider ajustado a: ${sliderPistas.value}")


        // 🔹 Ajustar el slider cuando el usuario cambie la dificultad en el Spinner
        spinnerDificultad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val dificultadSeleccionada = parent?.getItemAtPosition(position).toString()
                editor.putString("DIFICULTAD", dificultadSeleccionada)
                editor.apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        // 🔹 Guardar el valor del slider cuando el usuario lo modifique manualmente
        sliderPistas.addOnChangeListener { _, value, _ ->
            val pistasSeleccionadas = value.toInt()
            editor.putInt("NUM_HINTS", pistasSeleccionadas)
            editor.apply()
            Toast.makeText(this, getString(R.string.hints_count, pistasSeleccionadas), Toast.LENGTH_SHORT).show()
        }
    }
}
