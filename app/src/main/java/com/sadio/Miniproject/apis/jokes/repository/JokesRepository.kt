package com.sadio.Miniproject.apis.jokes.repository

import androidx.lifecycle.LiveData
import com.sadio.Miniproject.apis.jokes.model.JokesRetrofit
import com.sadio.Miniproject.apis.jokes.model.JokesRoom
import com.sadio.Miniproject.architecture.CustomApplication
import com.sadio.Miniproject.architecture.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JokesRepository {
    private val jokesDao = CustomApplication.instance.mApplicationDatabase.jokesDao()

    fun selectAllJokes(): LiveData<List<JokesRoom>> {
        return jokesDao.selectAll()
    }

    private suspend fun insertJoke(joke: JokesRoom) =
        withContext(Dispatchers.IO) {
            jokesDao.insert(joke)
        }

    fun deleteJokeWithTimestamp(timestamp: Long) {
        jokesDao.deleteWithTimestamp(timestamp)
    }

    suspend fun deleteAllJokes() = withContext(Dispatchers.IO) {
        jokesDao.deleteAll()
    }

    suspend fun fetchData() {
        insertJoke(RetrofitBuilder.getJoke().getRandomJoke().toRoom())
    }
}

private fun JokesRetrofit.toRoom(): JokesRoom {
    return JokesRoom(
        category = category,
        setup = setup,
        delivery = delivery,
        api = "Jokes api",
        timestamp = System.currentTimeMillis()
    )
}