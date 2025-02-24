package com.example.quizzapp_project

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes

data class Question(var Categoria:Int, @StringRes val questionId: Int, var numOpciones: Int, var opciones: List<Option>, var respondida: Boolean, var acierto: Boolean, var pistaUsada: Boolean, var OpcionSeleccionada: Int) {

    init {
        // Obtener la opción correcta; si no existe, lanza una excepción
        val opcionCorrecta = opciones.find { it.esCorrecta }
            ?: throw IllegalStateException("No se encontró la opción correcta")

        // Filtrar y barajar las opciones incorrectas
        val opcionesIncorrectas = opciones.filter { !it.esCorrecta }.shuffled()

        // Calcular cuántas opciones incorrectas necesitamos (siempre debe haber la correcta)
        val numIncorrectasNecesarias = numOpciones - 1

        // Seleccionar las opciones incorrectas necesarias (si hay suficientes)
        val seleccionadasIncorrectas = opcionesIncorrectas.take(numIncorrectasNecesarias)

        // Combinar la opción correcta con las incorrectas seleccionadas y barajar el resultado final
        opciones = (listOf(opcionCorrecta) + seleccionadasIncorrectas).shuffled()
    }

    fun ShuffleOptions() {
        opciones = opciones.shuffled()
    }
}