package com.example.quizzapp_project

import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text


class EndingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_ending)

        val correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)
        val incorrectAnswers = intent.getIntExtra("INCORRECT_ANSWERS", 0)

        val scoreText = findViewById<TextView>(R.id.score_text)
        val resultImage = findViewById<ImageView>(R.id.result_image)
        val finalText = findViewById<TextView>(R.id.final_text)
        val restartButton = findViewById<Button>(R.id.restart_button)
        val puntuacionPregunta = findViewById<TextView>(R.id.puntuacion_pregunta)
        val penalizacionPista = findViewById<TextView>(R.id.penalizacion_pista)
        val bonificacionPista = findViewById<TextView>(R.id.bonificacion_pista)
        val puntuacionFinal = findViewById<TextView>(R.id.puntuacion_final)

        scoreText.text = "Respuestas correctas: $correctAnswers\nRespuestas incorrectas: $incorrectAnswers"

        when {
            correctAnswers >= 8 -> resultImage.setImageResource(R.drawable.celebrate)
            correctAnswers >= 4 -> resultImage.setImageResource(R.drawable.pleased)
            else -> resultImage.setImageResource(R.drawable.bad)
        }

        when {
            correctAnswers >= 8 -> finalText.setText("¡Excelente!")
            correctAnswers >= 4 -> finalText.setText("¡Puedes mejorar!")
            else -> finalText.setText("¡Suerte para la próxima!")
        }



        restartButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}