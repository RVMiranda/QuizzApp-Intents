package com.example.quizzapp_project

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PlayActivity : AppCompatActivity() {
    private lateinit var hintsBtn : Button
    private lateinit var numQuestionText : TextView
    private lateinit var questionImg : ImageView
    private lateinit var questionText: TextView
    private lateinit var resultText : TextView

    //Opciones de botones A,B,C Y D
    private lateinit var optionA : Button
    private lateinit var optionB : Button
    private lateinit var optionC : Button
    private lateinit var optionD : Button

    //Botones de navegacion
    private lateinit var prevBtn : Button
    private lateinit var nextBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        hintsBtn = findViewById(R.id.hint_button) //Boton de pistas
        numQuestionText = findViewById(R.id.numQuestion_text) //Texto de pregunta actual (1/10)
        questionImg = findViewById(R.id.question_img) //Imagen de la categoria
        questionText = findViewById(R.id.question_text) //Texto de la pregunta
        resultText = findViewById(R.id.result_text) //Si la pregunta fue contestada correctamente o incorrectamente

        //Opciones de botones A,B,C Y D
        optionA = findViewById(R.id.optionA_button)
        optionB = findViewById(R.id.optionB_button)
        optionC = findViewById(R.id.optionC_button)
        optionD = findViewById(R.id.optionD_button)

        //Botones de navegacion
        prevBtn = findViewById(R.id.prev_button)
        nextBtn = findViewById(R.id.next_button)
    }
}