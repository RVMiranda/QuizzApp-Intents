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
    private var correctAnswers = 0
    private var incorrectAnswers = 0
    private var usedHints = 0
    private var numHints = 0
    private var numOptions = 0
    private var puntuacion_por_acierto = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_ending)

        correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)
        incorrectAnswers = intent.getIntExtra("INCORRECT_ANSWERS", 0)
        usedHints = intent.getIntExtra("USED_HINTS", 0)
        numHints = intent.getIntExtra("NUM_HINTS", 0)
        numOptions = intent.getIntExtra("DIFFICULT", 3)
        puntuacion_por_acierto = getPointsPerCorrectAnswer(numOptions)

        val scoreText = findViewById<TextView>(R.id.score_text)
        val resultImage = findViewById<ImageView>(R.id.result_image)
        val finalText = findViewById<TextView>(R.id.final_text)
        val restartButton = findViewById<Button>(R.id.restart_button)
        val puntuacionPregunta_text = findViewById<TextView>(R.id.puntuacion_pregunta)
        val penalizacionPista_text = findViewById<TextView>(R.id.penalizacion_pista)
        val bonificacionPista_text = findViewById<TextView>(R.id.bonificacion_pista)
        val puntuacionFinal_text = findViewById<TextView>(R.id.puntuacion_final)

        puntuacionPregunta_text.text = "Puntuación por pregunta correctas: ${puntuacion_por_acierto}"
        penalizacionPista_text.text = "Pistas utilizadas: ${usedHints}"
        bonificacionPista_text.text = "Pistas no utilizadas: ${numHints}"
        puntuacionFinal_text.text = "Puntuación final: ${calculateFinalPoints()}"
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

    private fun calculateFinalPoints() : Int {
        //Se multiplica los aciertos
        var penalizacionPorUsoHint = puntuacion_por_acierto / 2
        var bonificacionPorNoUsarHint = 10

        //Puntuaciones
        var puntuacionTotalPorAcierto = correctAnswers * puntuacion_por_acierto
        var penalizacionHint = penalizacionPorUsoHint * usedHints
        var bonificacionHint = bonificacionPorNoUsarHint * numHints

        return puntuacionTotalPorAcierto + bonificacionHint - penalizacionHint
    }

    private fun getPointsPerCorrectAnswer(dificultad: Int): Int {
        return when (dificultad) {
            2 -> 10
            3 -> 20
            4 -> 30
            else -> 10
        }
    }


}