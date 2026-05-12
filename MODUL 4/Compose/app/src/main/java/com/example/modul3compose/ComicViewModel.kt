package com.example.modul3compose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ComicViewModel(
    private val appName: String,
    private val repository: ComicRepository
): ViewModel() {
    private val _carouselComics = MutableStateFlow<List<Comic>>(emptyList())
    private val _listComics = MutableStateFlow<List<Comic>>(emptyList())

    private val _selectedComic = MutableStateFlow<Comic?>(null)
    val carouselComics = _carouselComics.asStateFlow()
    val listComics = _listComics.asStateFlow()

    val selectedComic = _selectedComic.asStateFlow()

    fun selectComic(comic: Comic) {
        _selectedComic.value = comic
    }

    init {
            _carouselComics.value = repository.getCarouselComics()
            _listComics.value = repository.getListComics()
    }
}