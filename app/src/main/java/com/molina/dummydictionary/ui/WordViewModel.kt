package com.molina.dummydictionary.ui

import androidx.lifecycle.ViewModel
import com.molina.dummydictionary.repository.DictionaryRepository

class WordViewModel(private val repository: DictionaryRepository): ViewModel() {
    val words = repository.words
}