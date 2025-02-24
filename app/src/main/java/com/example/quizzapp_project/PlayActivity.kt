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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class PlayActivity : AppCompatActivity() {
    private lateinit var quizAppModel: QuizViewModel

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
        val dificultad_str = prefs.getString("DIFICULTAD", "NORMAL") ?: "NORMAL"
        val NumHints = prefs.getInt("NUM_HINTS", 2) ?: 2
        var dificultad = 2

        when (dificultad_str) {
            "FÁCIL" -> dificultad = 2
            "NORMAL" -> dificultad = 3
            "DIFÍCIL" -> dificultad = 4
            else -> dificultad = 3
        }

        // Crear el Factory con los valores obtenidos
        val factory = QuizViewModelFactory(dificultad, NumHints)
        // Inicializar el ViewModel usando ViewModelProvider con el Factory
        quizAppModel = ViewModelProvider(this, factory).get(QuizViewModel::class.java)

        //Verifica si se crea el activity por un cambio de pantalla o porque el usuario le dio Start
        if (!quizAppModel.isFliping) {
            //No es un cambio de pantalla mezcla las preguntas y sus opciones
            quizAppModel.mixQuestionsAndOptions()
        }
        else {
            //Es un cambio de pantalla no revuelve las preguntas
            quizAppModel.isFliping = false
        }
        Toast.makeText(this, "Numero de pistas: ${quizAppModel.NumHints}", Toast.LENGTH_SHORT).show()

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

        nextBtn.setOnClickListener {
            quizAppModel.nextQuestion()
            actualizarPreguntas()
        }

        prevBtn.setOnClickListener {
            quizAppModel.prevQuestion()
            actualizarPreguntas()
        }

        hintsBtn.setOnClickListener { //Pendiente por desarrollar
//            val intent = Intent(this, HintActivity::class.java)
//            startActivity(intent)
            mostrarDialogoHints()
        }

        optionA.setOnClickListener {
            if (!quizAppModel.currentQuestion.respondida) {
                if (!quizAppModel.currentQuestion.opciones[0].deshabilitado) { //Si no ha sido descartada por una hint
                    if (quizAppModel.currentQuestion.opciones[0].esCorrecta) {
                        preguntaCorrecta()
                    }
                    else {
                        preguntaIncorrecta()
                    }
                }
                else Toast.makeText(this, "Esta respuesta no es chaval 🐰", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Esta pregunta ya ha sido respondida!", Toast.LENGTH_SHORT).show()
            }
        }
        optionB.setOnClickListener {
            if (!quizAppModel.currentQuestion.respondida) {
                if (!quizAppModel.currentQuestion.opciones[1].deshabilitado) {
                    if (quizAppModel.currentQuestion.opciones[1].esCorrecta) {
                        preguntaCorrecta()
                    } else {
                        preguntaIncorrecta()
                    }
                }
                else Toast.makeText(this, "Esta respuesta no es chaval 🐰", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Esta pregunta ya ha sido respondida!", Toast.LENGTH_SHORT).show()
            }
        }
        optionC.setOnClickListener {
            if (!quizAppModel.currentQuestion.respondida) {
                if (!quizAppModel.currentQuestion.opciones[2].deshabilitado) {
                    if (quizAppModel.currentQuestion.opciones[2].esCorrecta) {
                        preguntaCorrecta()
                    } else {
                        preguntaIncorrecta()
                    }
                }
                else Toast.makeText(this, "Esta respuesta no es chaval 🐰", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Esta pregunta ya ha sido respondida!", Toast.LENGTH_SHORT).show()
            }
        }
        optionD.setOnClickListener {
            if (!quizAppModel.currentQuestion.respondida) {
                if (!quizAppModel.currentQuestion.opciones[2].deshabilitado) {
                    if (quizAppModel.currentQuestion.opciones[3].esCorrecta) {
                        preguntaCorrecta()
                    } else {
                        preguntaIncorrecta()
                    }
                }
                else Toast.makeText(this, "Esta respuesta no es chaval 🐰", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Esta pregunta ya ha sido respondida!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun actualizarPreguntas() {
        questionText.setText(quizAppModel.currentQuestion.questionId)
        numQuestionText.text = getString(R.string.numQuestion, quizAppModel.CurrentIndex, quizAppModel.TotalQuestions)


        if (quizAppModel.currentQuestion.numOpciones > 1) {
            if (quizAppModel.currentQuestion.opciones[0].deshabilitado) optionA.setBackgroundColor(this.getColor(R.color.WrongOption))
            else optionA.setBackgroundColor(this.getColor(R.color.ColorOpcion))

            if (quizAppModel.currentQuestion.opciones[1].deshabilitado) optionB.setBackgroundColor(this.getColor(R.color.WrongOption))
            else optionB.setBackgroundColor(this.getColor(R.color.ColorOpcion))
            optionA.setText(quizAppModel.currentQuestion.opciones[0].optionId)
            optionB.setText(quizAppModel.currentQuestion.opciones[1].optionId)

            if (quizAppModel.currentQuestion.numOpciones > 2) {
                optionC.setText(quizAppModel.currentQuestion.opciones[2].optionId)
                if (quizAppModel.currentQuestion.opciones[2].deshabilitado) optionC.setBackgroundColor(this.getColor(R.color.WrongOption))
                else optionC.setBackgroundColor(this.getColor(R.color.ColorOpcion))
                if (quizAppModel.currentQuestion.numOpciones > 3) { //Modo Difícil
                    optionD.setText(quizAppModel.currentQuestion.opciones[3].optionId)
                    if (quizAppModel.currentQuestion.opciones[3].deshabilitado) optionD.setBackgroundColor(this.getColor(R.color.WrongOption))
                    else optionD.setBackgroundColor(this.getColor(R.color.ColorOpcion))
                }
                else { //Modo Normal
                    optionD.visibility = View.GONE
                    spaceCyD.visibility = View.GONE
                }
            }
            else { //Modo Fácil
                optionC.visibility = View.GONE
                optionD.visibility = View.GONE
            }
        }




        //Cambia la imagen de la pregunta segun su categoria
        when(quizAppModel.currentQuestion.Categoria) {
            // Tema: Geografía   (Categoría 1)
            1 -> {
                questionImg.setBackgroundResource(R.drawable.geografia)
            }
            // Tema: Historia  (Categoría 2)
            2 -> {
                questionImg.setBackgroundResource(R.drawable.historia)
            }
            // Tema: Ciencia (Categoría 3)
            3 -> {
                questionImg.setBackgroundResource(R.drawable.ciencia)
            }
            // Tema: Literatura (Categoría 4)
            4 -> {
                questionImg.setBackgroundResource(R.drawable.literatura)
            }
            // Tema: Deportes (Categoría 5)
            5 -> {
                questionImg.setBackgroundResource(R.drawable.deportes)
            }
            else -> {
                questionImg.setBackgroundResource(R.drawable.astronauta)
            }
        }

        //Texto de resultado -> Correcto / Incorrecto
        if (quizAppModel.currentQuestion.respondida) {
            if (quizAppModel.currentQuestion.acierto) { //Pregunta respondida correctamente
                if (quizAppModel.currentQuestion.pistaUsada) {
                    resultText.text = getString(R.string.result_hint, getString(R.string.result_ok)) //Texto para diferenciar si utilizó una pista en la pregunta
                }
                else {
                    resultText.setText(R.string.result_ok)
                }
                resultText.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            else { //Pregunta respondida incorrectamente
                if (quizAppModel.currentQuestion.pistaUsada) {
                    resultText.text = getString(R.string.result_hint, getString(R.string.result_bad))
                }
                else {
                    resultText.setText(R.string.result_bad)
                }
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

        if (quizAppModel.currentQuestion.pistaUsada) { //Si el usuario uso una pista en la pregunta
            resultText.text = getString(R.string.result_hint, getString(R.string.result_ok)) //Texto para diferenciar si utilizó una pista en la pregunta
        }
        else {
            resultText.setText(R.string.result_ok) //Si el usuario NO uso una pista
            quizAppModel.RachaAciertos = 1
            Toast.makeText(this, "Respuesta correcta! :) Racha: ${quizAppModel.RachaAciertos} Hints: ${quizAppModel.NumHints}", Toast.LENGTH_SHORT).show()
        }

        resultText.setTextColor(ContextCompat.getColor(this, R.color.green))


        checkEndGame()
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
            quizAppModel.RachaAciertos = -1
        }

        resultText.setTextColor(ContextCompat.getColor(this, R.color.red))

        Toast.makeText(this, "Respuesta incorrecta! :(", Toast.LENGTH_SHORT).show()

        checkEndGame()
    }

    private fun mostrarDialogoHints() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pistas 💡")
        builder.setMessage("¿Estás seguro de usar una pista?")

        // Botón "Sí"
        builder.setPositiveButton("Sí") { dialog, _ ->
            // Acción cuando el usuario presiona "Sí"
            if (!quizAppModel.currentQuestion.respondida) {
                val aux = quizAppModel.useHint()
                actualizarPreguntas()
                if (aux == 1) Toast.makeText(
                    this,
                    "Pistas restantes: ${quizAppModel.NumHints}",
                    Toast.LENGTH_SHORT
                ).show()
                else if (aux == 2) Toast.makeText(
                    this,
                    "Pregunta respondida con pistas, Pistas restantes: ${quizAppModel.NumHints}",
                    Toast.LENGTH_SHORT
                ).show()
                else Toast.makeText(
                    this,
                    "No tienes pistas! :(",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else Toast.makeText(this, "No puedes usar una pista en una pregunta respondida!", Toast.LENGTH_SHORT).show()
            dialog.dismiss() // Cierra el diálogo
        }

        // Botón "No"
        builder.setNegativeButton("No") { dialog, _ ->
            // Acción cuando el usuario presiona "No"
            Toast.makeText(this, "Bien hecho 😊", Toast.LENGTH_SHORT).show()
            dialog.dismiss() // Cierra el diálogo
        }

        // Mostrar el diálogo
        builder.show()
    }

    private fun checkEndGame() {
        if (quizAppModel.getAnsweredCount() >= quizAppModel.NumQuestions) {
            val intent = Intent(this, EndingActivity::class.java).apply {
                putExtra("CORRECT_ANSWERS", quizAppModel.getCorrectAnswersCount())
                putExtra("INCORRECT_ANSWERS", quizAppModel.getIncorrectAnswersCount())
                putExtra("USED_HINTS", quizAppModel.getHintUsedCount())
                putExtra("NUM_HINTS", quizAppModel.NumHints)
                putExtra("DIFFICULT", quizAppModel.NumOpciones)
            }
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        if (isChangingConfigurations) {
            //La activity se está destruyendo por rotacion de la pantalla entonces evitamos que reinicie la posicion de las preguntas.
            quizAppModel.isFliping = true
        }
        super.onDestroy()
    }
}