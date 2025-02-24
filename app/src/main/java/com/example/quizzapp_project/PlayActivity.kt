package com.example.quizzapp_project

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Space
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

    private lateinit var spaceCyD : Space

    //Botones de navegacion
    private lateinit var prevBtn : Button
    private lateinit var nextBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE)
        val dificultad = prefs.getString("DIFICULTAD", "NORMAL") ?: "NORMAL"

        Toast.makeText(this, "Dificultad: $dificultad", Toast.LENGTH_SHORT).show()

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

        spaceCyD = findViewById(R.id.space_CyD)

        //Botones de navegacion
        prevBtn = findViewById(R.id.prev_button)
        nextBtn = findViewById(R.id.next_button)

        //Setup de la primera pregunta y sus opciones
        actualizarPreguntas()

        optionA.setOnClickListener { validarRespuesta(0) }
        optionB.setOnClickListener { validarRespuesta(1) }
        optionC.setOnClickListener { if (opcionesMostradas.size > 2) validarRespuesta(2) }
        optionD.setOnClickListener { if (opcionesMostradas.size > 3) validarRespuesta(3) }


        nextBtn.setOnClickListener {
            quizAppModel.nextQuestion()
            actualizarPreguntas()
        }

        prevBtn.setOnClickListener {
            quizAppModel.prevQuestion()
            actualizarPreguntas()
        }

        hintsBtn.setOnClickListener { //Pendiente por desarrollar
            val intent = Intent(this, HintActivity::class.java)
            startActivity(intent)
        }


    }

//    private fun actualizarPreguntas() {
//        questionText.setText(quizAppModel.currentQuestion.questionId)
//        numQuestionText.text = getString(R.string.numQuestion, quizAppModel.CurrentIndex, quizAppModel.TotalQuestions)
//
//
//        if (quizAppModel.currentQuestion.numOpciones > 1) {
//            optionA.setText(quizAppModel.currentQuestion.opciones[0].optionId)
//            optionB.setText(quizAppModel.currentQuestion.opciones[1].optionId)
//
//            if (quizAppModel.currentQuestion.numOpciones > 2) {
//                optionC.setText(quizAppModel.currentQuestion.opciones[2].optionId)
//
//                if (quizAppModel.currentQuestion.numOpciones > 3) { //Modo Difícil
//                    optionD.setText(quizAppModel.currentQuestion.opciones[3].optionId)
//                }
//                else { //Modo Normal
//                    optionD.visibility = View.GONE
//                    spaceCyD.visibility = View.GONE
//                }
//            }
//            else { //Modo Fácil
//                optionC.visibility = View.GONE
//                optionD.visibility = View.GONE
//            }
//        }
//
//
//
//
//        //Cambia la imagen de la pregunta segun su categoria
//        when(quizAppModel.currentQuestion.Categoria) {
//            // Tema: Geografía   (Categoría 1)
//            1 -> {
//                questionImg.setBackgroundResource(R.drawable.geografia)
//            }
//            // Tema: Historia  (Categoría 2)
//            2 -> {
//                questionImg.setBackgroundResource(R.drawable.historia)
//            }
//            // Tema: Ciencia (Categoría 3)
//            3 -> {
//                questionImg.setBackgroundResource(R.drawable.ciencia)
//            }
//            // Tema: Literatura (Categoría 4)
//            4 -> {
//                questionImg.setBackgroundResource(R.drawable.literatura)
//            }
//            // Tema: Deportes (Categoría 5)
//            5 -> {
//                questionImg.setBackgroundResource(R.drawable.deportes)
//            }
//            else -> {
//                questionImg.setBackgroundResource(R.drawable.astronauta)
//            }
//        }
//
//        //Texto de resultado -> Correcto / Incorrecto
//        if (quizAppModel.currentQuestion.respondida) {
//            if (quizAppModel.currentQuestion.acierto) { //Pregunta respondida correctamente
//                if (quizAppModel.currentQuestion.pistaUsada) {
//                    resultText.text = getString(R.string.result_hint, getString(R.string.result_ok)) //Texto para diferenciar si utilizó una pista en la pregunta
//                }
//                else {
//                    resultText.setText(R.string.result_ok)
//                }
//                resultText.setTextColor(ContextCompat.getColor(this, R.color.green))
//            }
//            else { //Pregunta respondida incorrectamente
//                if (quizAppModel.currentQuestion.pistaUsada) {
//                    resultText.text = getString(R.string.result_hint, getString(R.string.result_bad))
//                }
//                else {
//                    resultText.setText(R.string.result_bad)
//                }
//                resultText.setTextColor(ContextCompat.getColor(this, R.color.red))
//            }
//        }
//        else {
//            resultText.text = ""
//        }
//    }

    private var opcionesMostradas: List<Option> = emptyList()

    private fun actualizarPreguntas() {
        questionText.setText(quizAppModel.currentQuestion.questionId)
        numQuestionText.text = getString(R.string.numQuestion, quizAppModel.CurrentIndex, quizAppModel.TotalQuestions)

        val prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE)
        val dificultad = prefs.getString("DIFICULTAD", "NORMAL") ?: "NORMAL"

        val todasLasOpciones = quizAppModel.currentQuestion.opciones.toMutableList()
        val opcionCorrecta = todasLasOpciones.find { it.esCorrecta } ?: todasLasOpciones[0]

        opcionesMostradas = when (dificultad) {
            "FÁCIL" -> {
                val incorrectas = todasLasOpciones.filter { !it.esCorrecta }.shuffled().take(1)
                listOf(opcionCorrecta) + incorrectas
            }
            "NORMAL" -> {
                val incorrectas = todasLasOpciones.filter { !it.esCorrecta }.shuffled().take(2)
                listOf(opcionCorrecta) + incorrectas
            }
            "DIFÍCIL" -> todasLasOpciones
            else -> todasLasOpciones
        }.shuffled()

        // ✅ Limpiar el resultado si la pregunta aún no ha sido respondida
        if (!quizAppModel.currentQuestion.respondida) {
            resultText.text = ""
        } else {
            // ✅ Si la pregunta ya fue respondida, mostrar si fue correcta o incorrecta
            if (quizAppModel.currentQuestion.acierto) {
                resultText.setText(R.string.result_ok)
                resultText.setTextColor(ContextCompat.getColor(this, R.color.green))
            } else {
                resultText.setText(R.string.result_bad)
                resultText.setTextColor(ContextCompat.getColor(this, R.color.red))
            }
        }

        // Asignar opciones a botones y ocultar las que no se usan
        optionA.setText(opcionesMostradas[0].optionId)
        optionB.setText(opcionesMostradas[1].optionId)
        optionA.visibility = View.VISIBLE
        optionB.visibility = View.VISIBLE

        if (opcionesMostradas.size > 2) {
            optionC.setText(opcionesMostradas[2].optionId)
            optionC.visibility = View.VISIBLE
        } else {
            optionC.visibility = View.GONE
        }

        if (opcionesMostradas.size > 3) {
            optionD.setText(opcionesMostradas[3].optionId)
            optionD.visibility = View.VISIBLE
        } else {
            optionD.visibility = View.GONE
        }
    }


    private fun preguntaCorrecta() {
        //Poner lo que se haria en caso de que sea correcta.
        quizAppModel.currentQuestion.respondida = true
        quizAppModel.currentQuestion.acierto = true

        //Texto que muestra que fue contestada correctamente

        if (quizAppModel.currentQuestion.pistaUsada) { //Si el usuario uso una pista en la pregunta
            resultText.text = getString(R.string.result_hint, getString(R.string.result_ok)) //Texto para diferenciar si utilizó una pista en la pregunta
        }
        else {
            resultText.setText(R.string.result_ok) //Si el usuario NO uso una pista
        }

        resultText.setTextColor(ContextCompat.getColor(this, R.color.green))
        Toast.makeText(this, "Respuesta correcta! :)", Toast.LENGTH_SHORT).show()
    }

    private fun preguntaIncorrecta() {
        //Poner lo que se haria en caso de que sea incorrecta.
        quizAppModel.currentQuestion.respondida = true
        quizAppModel.currentQuestion.acierto = false

        //Texto que muestra que fue contestada correctamente
        if (quizAppModel.currentQuestion.pistaUsada) {
            resultText.text = getString(R.string.result_hint, getString(R.string.result_bad))
        }
        else {
            resultText.setText(R.string.result_bad)
        }

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

    private fun validarRespuesta(index: Int) {
        if (!quizAppModel.currentQuestion.respondida) {
            if (index >= 0 && index < opcionesMostradas.size) { // Asegurar que el índice es válido
                val opcionSeleccionada = opcionesMostradas[index] // Tomar la opción desde las mostradas en pantalla
                if (opcionSeleccionada.esCorrecta) {
                    preguntaCorrecta()
                } else {
                    preguntaIncorrecta()
                }
            } else {
                Toast.makeText(this, "Error: Índice fuera de rango.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Esta pregunta ya ha sido respondida!", Toast.LENGTH_SHORT).show()
        }
    }




}