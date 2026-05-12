package com.example.modul3xml.viewmodel

import androidx.lifecycle.ViewModel
import com.example.modul3xml.model.Comic
import com.example.modul3xml.repository.ComicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.collections.emptyList

class ComicViewModel(
    private val appName: String,
    private val repo: ComicRepository
) : ViewModel() {
    private val _carouselComics = MutableStateFlow<List<Comic>>(emptyList())
    private val _listComics = MutableStateFlow<List<Comic>>(emptyList())
    private val _selectedComic = MutableStateFlow<Comic?>(null)

    val carouselComics: StateFlow<List<Comic>> = _carouselComics.asStateFlow()
    val listComics: StateFlow<List<Comic>> = _listComics.asStateFlow()
    val selectedComic: StateFlow<Comic?> = _selectedComic.asStateFlow()

    fun selectComic(comic: Comic) {
        _selectedComic.value = comic
    }

    init {
        _carouselComics.value = repo.getCarouselComics()
        _listComics.value = repo.getListComics()
    }
}
