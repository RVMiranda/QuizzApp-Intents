package com.example.quizzapp_project

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HintActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hint)

        val prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE)
        val numHints = prefs.getInt("NUM_HINTS", 2) // Valor por defecto: 2 pistas

        val hintText = findViewById<TextView>(R.id.hint_text)
        hintText.text = "Tienes $numHints pistas disponibles"
    }
}