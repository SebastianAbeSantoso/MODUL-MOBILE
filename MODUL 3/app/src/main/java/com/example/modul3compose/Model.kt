package com.example.modul3compose

import android.os.Message

data class Comic (
    val title: String,
    val coverImage: Int,
    val backgroundImage: Int,
    val url: String,
    val description: String,
    val genre: String,
    val author: String
)

sealed class UiState {
    object Loading : UiState()
    data class Success(val listComics: List<Comic>, val carouselComics: List<Comic>) : UiState()
    data class Error(val message: String) : UiState()
}