package com.example.hoge.roomwithaview.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.hoge.roomwithaview.entity.Word

@Dao
interface WordDao {
    @Insert
    fun insert(word: Word)

    @Query("DELETE from word_table")
    fun deleteAll()

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>
}