package com.example.modul4compose

data class Comic (
    val title: String,
    val coverImage: Int,
    val backgroundImage: Int,
    val url: String,
    val description: List<Int>,
    val genres: List<Int>,
    val author: String,
    val id: Int
)