package com.example.modul3xml.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul3xml.repository.ComicRepository
import com.example.modul3xml.viewmodel.ComicViewModel

class ComicViewModelFactory(
    private val appName: String,
    private val repository: ComicRepository
): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ComicViewModel::class.java)){
                return ComicViewModel(appName, repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel")
        }
}