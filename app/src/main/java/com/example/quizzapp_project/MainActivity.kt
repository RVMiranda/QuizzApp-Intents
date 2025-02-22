package com.example.quizzapp_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnJugar: Button
    private lateinit var btnOpciones: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnJugar = findViewById(R.id.btnPlay)
        btnOpciones = findViewById(R.id.btnOptions)

        btnJugar.setOnClickListener {
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
        }
        btnOpciones.setOnClickListener {
            val intent = Intent(this, ActivitySettings::class.java)
            startActivity(intent)
        }
    }
}