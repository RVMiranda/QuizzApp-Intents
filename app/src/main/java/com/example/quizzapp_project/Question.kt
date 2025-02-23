package com.example.quizzapp_project

import androidx.annotation.StringRes

data class Question(var Categoria:Int, @StringRes val questionId: Int, var opciones: List<Option>, var respondida: Boolean, var acierto: Boolean, var pistaUsada: Boolean) {
    fun ShuffleOptions() {
        opciones = opciones.shuffled()
    }
}