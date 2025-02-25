package com.example.quizzapp_project

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.lifecycle.ViewModel

class QuizViewModel(val Dificultad: Int, val NUMPistas: Int) : ViewModel() {
    var isFliping = false //Para detectar cuando se voltea la pantalla
    private var currentIndex = 0
    private var DEFAULT_NUM_QUESTIONS = 10 //Cuidar que este numero sea igual o mayor al numero de preguntas en questionBank
    private var NUM_OPTIONS = 3 //Numero de opciones minimo 2 maximo 4 (facil=2, normal=3 y dificil=4)

    private var rachaAciertos = 0

    //Para calcular su puntuación final.
    private var NUM_HINTS = 3

    val NumQuestions: Int
        get() = DEFAULT_NUM_QUESTIONS

    var NumOpciones: Int
        get() = NUM_OPTIONS
        set(value) {
            if (value < 2) NUM_OPTIONS = 2
            else if (value > 4) NUM_OPTIONS = 4
            else NUM_OPTIONS = value
        }

    var RachaAciertos: Int
        get() = rachaAciertos
        set(value) { //Si recibe -1 se reinicia la racha
            if (value < 0) {
                rachaAciertos = 0
            }
            else { //Si recibe un numero mayor a 0 se le suma uno a la racha
                rachaAciertos++
                if (rachaAciertos % 2 == 0) { //Si tiene 2 buenas seguidas se bonifica con una hint.
                    NUM_HINTS++
                }
            }
        }

    var NumHints: Int
        get() = NUM_HINTS
        set(value: Int) {
            if (value >= 0) {
                NUM_HINTS = value
            }
            else {
                NUM_HINTS = 0
            }
        }

    val currentQuestion: Question
        get() = questionBank[currentIndex]

    val CurrentIndex: Int
        get() = currentIndex + 1

    val TotalQuestions: Int
        get() = DEFAULT_NUM_QUESTIONS


    init {
        NUM_OPTIONS = Dificultad
        NUM_HINTS = NUMPistas
    }

    //Actualmente 25 preguntas en total de 5 temas diferentes
    private var questionBank = listOf(
        // Tema: Geografía   (Categoría 1)
        Question(
            1,
            R.string.question_text_1,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q1, true, false, false),
                Option(R.string.option2Q1, false, false, false),
                Option(R.string.option3Q1, false, false, false),
                Option(R.string.option4Q1, false, false, false)
            ),
            false,
            false,
            false,
            -1
        ),
        Question(
            1,
            R.string.question_text_2,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q2, true, false, false),
                Option(R.string.option2Q2, false, false, false),
                Option(R.string.option3Q2, false, false, false),
                Option(R.string.option4Q2, false, false, false)
            ),
            false,
            false,
            false,
            -1
        ),
        Question(
            1,
            R.string.question_text_3,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q3, true, false, false),
                Option(R.string.option2Q3, false, false, false),
                Option(R.string.option3Q3, false, false, false),
                Option(R.string.option4Q3, false, false, false)
            ),
            false,
            false,
            false,
            -1
        ),
        Question(
            1,
            R.string.question_text_4,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q4, true, false, false),
                Option(R.string.option2Q4, false, false, false),
                Option(R.string.option3Q4, false, false, false),
                Option(R.string.option4Q4, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            1,
            R.string.question_text_5,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q5, true, false, false),
                Option(R.string.option2Q5, false, false, false),
                Option(R.string.option3Q5, false, false, false),
                Option(R.string.option4Q5, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        // Tema: Historia  (Categoría 2)
        Question(
            2,
            R.string.question_text_6,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q6, true, false, false),
                Option(R.string.option2Q6, false, false, false),
                Option(R.string.option3Q6, false, false, false),
                Option(R.string.option4Q6, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            2,
            R.string.question_text_7,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q7, true, false, false),
                Option(R.string.option2Q7, false, false, false),
                Option(R.string.option3Q7, false, false, false),
                Option(R.string.option4Q7, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            2,
            R.string.question_text_8,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q8, true, false, false),
                Option(R.string.option2Q8, false, false, false),
                Option(R.string.option3Q8, false, false, false),
                Option(R.string.option4Q8, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            2,
            R.string.question_text_9,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q9, true, false, false),
                Option(R.string.option2Q9, false, false, false),
                Option(R.string.option3Q9, false, false, false),
                Option(R.string.option4Q9, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            2,
            R.string.question_text_10,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q10, true, false, false),
                Option(R.string.option2Q10, false, false, false),
                Option(R.string.option3Q10, false, false, false),
                Option(R.string.option4Q10, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        // Tema: Ciencia (Categoría 3)
        Question(
            3,
            R.string.question_text_11,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q11, true, false, false),
                Option(R.string.option2Q11, false, false, false),
                Option(R.string.option3Q11, false, false, false),
                Option(R.string.option4Q11, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            3,
            R.string.question_text_12,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q12, true, false, false),
                Option(R.string.option2Q12, false, false, false),
                Option(R.string.option3Q12, false, false, false),
                Option(R.string.option4Q12, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            3,
            R.string.question_text_13,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q13, true, false, false),
                Option(R.string.option2Q13, false, false, false),
                Option(R.string.option3Q13, false, false, false),
                Option(R.string.option4Q13, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            3,
            R.string.question_text_14,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q14, true, false, false),
                Option(R.string.option2Q14, false, false, false),
                Option(R.string.option3Q14, false, false, false),
                Option(R.string.option4Q14, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            3,
            R.string.question_text_15,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q15, true, false, false),
                Option(R.string.option2Q15, false, false, false),
                Option(R.string.option3Q15, false, false, false),
                Option(R.string.option4Q15, false, false, false)
            ),
            false,
            false,
            false,-1
        ),

        // Tema: Literatura (Categoría 4)
        Question(
            4,
            R.string.question_text_16,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q16, true, false, false),
                Option(R.string.option2Q16, false, false, false),
                Option(R.string.option3Q16, false, false, false),
                Option(R.string.option4Q16, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            4,
            R.string.question_text_17,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q17, true, false, false),
                Option(R.string.option2Q17, false, false, false),
                Option(R.string.option3Q17, false, false, false),
                Option(R.string.option4Q17, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            4,
            R.string.question_text_18,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q18, true, false, false),
                Option(R.string.option2Q18, false, false, false),
                Option(R.string.option3Q18, false, false, false),
                Option(R.string.option4Q18, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            4,
            R.string.question_text_19,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q19, true, false, false),
                Option(R.string.option2Q19, false, false, false),
                Option(R.string.option3Q19, false, false, false),
                Option(R.string.option4Q19, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            4,
            R.string.question_text_20,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q20, true, false, false),
                Option(R.string.option2Q20, false, false, false),
                Option(R.string.option3Q20, false, false, false),
                Option(R.string.option4Q20, false, false, false)
            ),
            false,
            false,
            false,-1
        ),

        // Tema: Deportes (Categoría 5)
        Question(
            5,
            R.string.question_text_21,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q21, true, false, false),
                Option(R.string.option2Q21, false, false, false),
                Option(R.string.option3Q21, false, false, false),
                Option(R.string.option4Q21, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            5,
            R.string.question_text_22,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q22, true, false, false),
                Option(R.string.option2Q22, false, false, false),
                Option(R.string.option3Q22, false, false, false),
                Option(R.string.option4Q22, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            5,
            R.string.question_text_23,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q23, true, false, false),
                Option(R.string.option2Q23, false, false, false),
                Option(R.string.option3Q23, false, false, false),
                Option(R.string.option4Q23, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            5,
            R.string.question_text_24,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q24, true, false, false),
                Option(R.string.option2Q24, false, false, false),
                Option(R.string.option3Q24, false, false, false),
                Option(R.string.option4Q24, false, false, false)
            ),
            false,
            false,
            false,-1
        ),
        Question(
            5,
            R.string.question_text_25,
            NUM_OPTIONS,
            listOf(
                Option(R.string.option1Q25, true, false, false),
                Option(R.string.option2Q25, false, false, false),
                Option(R.string.option3Q25, false, false, false),
                Option(R.string.option4Q25, false, false, false)
            ),
            false,
            false,
            false,-1
        )
    )

    //0: No tienes pistas / 1: Usaste una pista y aun hay opciones malas / 2: Usaste pista para contestar pregunta
    fun useHint(): Int {
        if (NumHints <= 0 || currentQuestion.respondida) return -1

        currentQuestion.pistaUsada = true
        NumHints--
        val resultQuestion = currentQuestion.deshabiliteWrongOption()
        return when (resultQuestion) {
            1 -> 1
            2 -> {
                currentQuestion.acierto = true
                currentQuestion.respondida = true
                2
            }
            else -> 0
        }
    }

    fun ResaltarOpcion(index: Int) {
        currentQuestion.opciones[index].seleccionada = true
    }


    fun mixQuestionsAndOptions() {
        //Mezcla las opciones utilizando el metodo interno de la clase Question
        for (question in questionBank) {
            question.ShuffleOptions()
        }
        //Mezcla las preguntas despues que sus opciones fueran mezcladas
        questionBank = questionBank.shuffled()
    }

    fun nextQuestion() {
        currentIndex = (currentIndex + 1) % DEFAULT_NUM_QUESTIONS
    }

    fun prevQuestion() {
        currentIndex = (currentIndex - 1 + DEFAULT_NUM_QUESTIONS) % DEFAULT_NUM_QUESTIONS
    }

    fun getAnsweredCount(): Int {
        return questionBank.count { it.respondida }
    }

    fun getCorrectAnswersCount(): Int {
        return questionBank.count { it.respondida && it.acierto }
    }

    fun getHintUsedCount(): Int {
        return questionBank.count { it.respondida && it.pistaUsada }
    }

    fun getIncorrectAnswersCount(): Int {
        return questionBank.count { it.respondida && !it.acierto }
    }

    override fun onCleared() {
        super.onCleared()
    }
}