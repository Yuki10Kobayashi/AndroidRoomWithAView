package com.example.hoge.roomwithaview.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import com.example.hoge.roomwithaview.dao.WordDao
import com.example.hoge.roomwithaview.entity.Word

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var instance: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            if (instance == null) {
                synchronized(WordRoomDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(context.applicationContext,
                                WordRoomDatabase::class.java, "word_database")
                                .addCallback(object : RoomDatabase.Callback() {
                                    override fun onOpen(db: SupportSQLiteDatabase) {
                                        PopulateDbAsync(instance!!).execute()
                                    }
                                })
                                .build()
                    }
                }
            }
            return instance!!
        }
    }
}

class PopulateDbAsync(db: WordRoomDatabase): AsyncTask<Void, Void, Void>() {
    private val mDao: WordDao = db.wordDao()

    override fun doInBackground(vararg params: Void?): Void? {
        mDao.deleteAll()
        var word = Word("Hello")
        mDao.insert(word)
        word = Word("World")
        mDao.insert(word)
        return null
    }
}