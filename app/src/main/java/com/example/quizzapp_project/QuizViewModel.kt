package com.example.quizzapp_project

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    private var currentIndex = 0
    private var DEFAULT_NUM_QUESTIONS = 10 //Cuidar que este numero sea igual o mayor al numero de preguntas en questionBank
    private var questionBank = listOf(
        Question(
            1,
            R.string.question_text_1,
            listOf(
                Option(R.string.option1Q1, true),
                Option(R.string.option2Q1, false),
                Option(R.string.option3Q1, false),
                Option(R.string.option4Q1, false)
            ),
            false,
            false,
            false
        ),
        Question(
            1,
            R.string.question_text_2,
            listOf(
                Option(R.string.option1Q2, true),
                Option(R.string.option2Q2, false),
                Option(R.string.option3Q2, false),
                Option(R.string.option4Q2, false)
            ),
            false,
            false,
            false
        ),
        Question(
            1,
            R.string.question_text_3,
            listOf(
                Option(R.string.option1Q3, true),
                Option(R.string.option2Q3, false),
                Option(R.string.option3Q3, false),
                Option(R.string.option4Q3, false)
            ),
            false,
            false,
            false
        ),
        Question(
            1,
            R.string.question_text_4,
            listOf(
                Option(R.string.option1Q4, true),
                Option(R.string.option2Q4, false),
                Option(R.string.option3Q4, false),
                Option(R.string.option4Q4, false)
            ),
            false,
            false,
            false
        ),
        Question(
            1,
            R.string.question_text_5,
            listOf(
                Option(R.string.option1Q5, true),
                Option(R.string.option2Q5, false),
                Option(R.string.option3Q5, false),
                Option(R.string.option4Q5, false)
            ),
            false,
            false,
            false
        ),
        Question(
            1,
            R.string.question_text_6,
            listOf(
                Option(R.string.option1Q6, true),
                Option(R.string.option2Q6, false),
                Option(R.string.option3Q6, false),
                Option(R.string.option4Q6, false)
            ),
            false,
            false,
            false
        ),
        Question(
            1,
            R.string.question_text_7,
            listOf(
                Option(R.string.option1Q7, true),
                Option(R.string.option2Q7, false),
                Option(R.string.option3Q7, false),
                Option(R.string.option4Q7, false)
            ),
            false,
            false,
            false
        ),
        Question(
            1,
            R.string.question_text_8,
            listOf(
                Option(R.string.option1Q8, true),
                Option(R.string.option2Q8, false),
                Option(R.string.option3Q8, false),
                Option(R.string.option4Q8, false)
            ),
            false,
            false,
            false
        ),
        Question(
            1,
            R.string.question_text_9,
            listOf(
                Option(R.string.option1Q9, true),
                Option(R.string.option2Q9, false),
                Option(R.string.option3Q9, false),
                Option(R.string.option4Q9, false)
            ),
            false,
            false,
            false
        ),
        Question(
            1,
            R.string.question_text_10,
            listOf(
                Option(R.string.option1Q10, true),
                Option(R.string.option2Q10, false),
                Option(R.string.option3Q10, false),
                Option(R.string.option4Q10, false)
            ),
            false,
            false,
            false
        )
    )

    fun mixQuestionsAndOptions() {
        //Mezcla las opciones utilizando el metodo interno de la clase Question
        for (question in questionBank) {
            question.ShuffleOptions()
        }

        //Mezcla las preguntas despues que sus opciones fueran mezcladas
        questionBank = questionBank.shuffled()
    }

    val currentQuestion: Question
        get() = questionBank[currentIndex]

    fun nextQuestion() {
        currentIndex = (currentIndex + 1) % DEFAULT_NUM_QUESTIONS
    }

    fun prevQuestion() {
        currentIndex = (currentIndex - 1 + DEFAULT_NUM_QUESTIONS) % DEFAULT_NUM_QUESTIONS
    }

    init {
    }

    override fun onCleared() {
        super.onCleared()
    }
}