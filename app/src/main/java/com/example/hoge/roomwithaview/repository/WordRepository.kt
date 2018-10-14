package com.example.hoge.roomwithaview.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.hoge.roomwithaview.dao.WordDao
import com.example.hoge.roomwithaview.db.WordRoomDatabase
import com.example.hoge.roomwithaview.entity.Word

class WordRepository(application: Application) {
    private val mWordDao: WordDao
    private var mAllWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        mWordDao = db.wordDao()
        mAllWords = mWordDao.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWords
    }

    fun insert(word: Word) {
        InsertAsyncTask(mWordDao).execute(word)
    }
}

class InsertAsyncTask(wordDao: WordDao): AsyncTask<Word, Void, Void>() {
    private val mAsyncTaskDao: WordDao = wordDao
    override fun doInBackground(vararg params: Word?): Void? {
        mAsyncTaskDao.insert(params[0]!!)
        return null
    }
}