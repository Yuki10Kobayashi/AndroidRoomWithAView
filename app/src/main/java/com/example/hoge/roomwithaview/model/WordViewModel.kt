package com.example.hoge.roomwithaview.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.hoge.roomwithaview.entity.Word
import com.example.hoge.roomwithaview.repository.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: WordRepository = WordRepository(application)
    var mAllWords: LiveData<List<Word>>

    init {
        mAllWords = mRepository.getAllWords()
    }

    fun insert(word: Word) {
        mRepository.insert(word)
    }
}