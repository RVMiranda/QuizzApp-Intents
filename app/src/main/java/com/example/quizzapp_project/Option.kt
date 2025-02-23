package com.example.quizzapp_project

import androidx.annotation.StringRes

data class Option(@StringRes val optionId: Int, var esCorrecta: Boolean) {
}