package com.example.modul4compose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

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
        Timber.d("Comic dipilih: $comic ")
        _selectedComic.value = comic
    }

    init {
        _listComics.value = repository.getListComics()
        Timber.d("Data item masuk ke dalam list ${_listComics.value}")

        _carouselComics.value = repository.getCarouselComics()
        Timber.d("Data item masuk ke dalam list ${_carouselComics.value}")
    }
}