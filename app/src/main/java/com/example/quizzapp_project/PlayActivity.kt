package com.example.quizzapp_project

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PlayActivity : AppCompatActivity() {
    private val quizAppModel: QuizViewModel by viewModels()

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

        //Verifica si se crea el activity por un cambio de pantalla o porque el usuario le dio Start
        if (!quizAppModel.isFliping) {
            //No es un cambio de pantalla mezcla las preguntas y sus opciones
            quizAppModel.mixQuestionsAndOptions()
        }
        else {
            //Es un cambio de pantalla no revuelve las preguntas
            quizAppModel.isFliping = false
        }

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

        //Setup de la primera pregunta y sus opciones
        actualizarPreguntas()

        nextBtn.setOnClickListener {
            quizAppModel.nextQuestion()
            actualizarPreguntas()
        }

        prevBtn.setOnClickListener {
            quizAppModel.prevQuestion()
            actualizarPreguntas()
        }

        optionA.setOnClickListener {
            if (!quizAppModel.currentQuestion.respondida) {
                if (quizAppModel.currentQuestion.opciones[0].esCorrecta) {
                    preguntaCorrecta()
                }
                else {
                    preguntaIncorrecta()
                }
            }
            else {
                Toast.makeText(this, "Esta pregunta ya ha sido respondida!", Toast.LENGTH_SHORT).show()
            }
        }
        optionB.setOnClickListener {
            if (!quizAppModel.currentQuestion.respondida) {
                if (quizAppModel.currentQuestion.opciones[1].esCorrecta) {
                    preguntaCorrecta()
                }
                else {
                    preguntaIncorrecta()
                }
            }
            else {
                Toast.makeText(this, "Esta pregunta ya ha sido respondida!", Toast.LENGTH_SHORT).show()
            }
        }
        optionC.setOnClickListener {
            if (!quizAppModel.currentQuestion.respondida) {
                if (quizAppModel.currentQuestion.opciones[2].esCorrecta) {
                    preguntaCorrecta()
                }
                else {
                    preguntaIncorrecta()
                }
            }
            else {
                Toast.makeText(this, "Esta pregunta ya ha sido respondida!", Toast.LENGTH_SHORT).show()
            }
        }
        optionD.setOnClickListener {
            if (!quizAppModel.currentQuestion.respondida) {
                if (quizAppModel.currentQuestion.opciones[3].esCorrecta) {
                    preguntaCorrecta()
                }
                else {
                    preguntaIncorrecta()
                }
            }
            else {
                Toast.makeText(this, "Esta pregunta ya ha sido respondida!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun actualizarPreguntas() {
        questionText.setText(quizAppModel.currentQuestion.questionId)
        numQuestionText.text = getString(R.string.numQuestion, quizAppModel.CurrentIndex, quizAppModel.TotalQuestions)

        optionA.setText(quizAppModel.currentQuestion.opciones[0].optionId)
        optionB.setText(quizAppModel.currentQuestion.opciones[1].optionId)
        optionC.setText(quizAppModel.currentQuestion.opciones[2].optionId)
        optionD.setText(quizAppModel.currentQuestion.opciones[3].optionId)

        //Texto de resultado -> Correcto / Incorrecto
        if (quizAppModel.currentQuestion.respondida) {
            if (quizAppModel.currentQuestion.acierto) {
                resultText.setText(R.string.result_ok)
                resultText.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            else {
                resultText.setText(R.string.result_bad)
                resultText.setTextColor(ContextCompat.getColor(this, R.color.red))
            }
        }
        else {
            resultText.text = ""
        }
    }

    private fun preguntaCorrecta() {
        //Poner lo que se haria en caso de que sea correcta.
        quizAppModel.currentQuestion.respondida = true
        quizAppModel.currentQuestion.acierto = true

        //Texto que muestra que fue contestada correctamente
        resultText.setText(R.string.result_ok)
        resultText.setTextColor(ContextCompat.getColor(this, R.color.green))

        Toast.makeText(this, "Respuesta correcta! :)", Toast.LENGTH_SHORT).show()
    }

    private fun preguntaIncorrecta() {
        //Poner lo que se haria en caso de que sea incorrecta.
        quizAppModel.currentQuestion.respondida = true
        quizAppModel.currentQuestion.acierto = false

        //Texto que muestra que fue contestada correctamente
        resultText.setText(R.string.result_bad)
        resultText.setTextColor(ContextCompat.getColor(this, R.color.red))

        Toast.makeText(this, "Respuesta incorrecta! :(", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        if (isChangingConfigurations) {
            //La activity se está destruyendo por rotacion de la pantalla entonces evitamos que reinicie la posicion de las preguntas.
            quizAppModel.isFliping = true
        }
        super.onDestroy()
    }

}