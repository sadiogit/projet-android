package com.sadio.Miniproject.apis.jokes.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sadio.Miniproject.apis.jokes.model.JokesRoom

@Dao
interface JokesDao {

    @Query("SELECT * FROM jokes")
    fun selectAll() : LiveData<List<JokesRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(jokesRoom: JokesRoom)

    @Query("DELETE FROM jokes WHERE joke_timestamp = :timestamp")
    fun deleteWithTimestamp(timestamp: Long)

    @Query("DELETE FROM jokes")
    fun deleteAll()
}
