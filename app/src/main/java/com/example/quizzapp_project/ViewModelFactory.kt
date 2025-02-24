package com.example.quizzapp_project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuizViewModelFactory(private val dificultad: Int, private val numPistas: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuizViewModel(dificultad, numPistas) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

