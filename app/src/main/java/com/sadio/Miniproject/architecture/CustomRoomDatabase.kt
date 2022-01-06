package com.sadio.Miniproject.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sadio.Miniproject.apis.jokes.dao.JokesDao
import com.sadio.Miniproject.apis.jokes.model.JokesRoom

@Database(
    entities = [
        JokesRoom::class,
    ],
    version = 2,
    exportSchema = false
)

abstract class CustomRoomDatabase : RoomDatabase() {

    abstract fun jokesDao() : JokesDao
}

