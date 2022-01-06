package com.sadio.Miniproject.apis.jokes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.sadio.Miniproject.apis.jokes.model.JokesRoom
import com.sadio.Miniproject.apis.jokes.model.JokesUi
import com.sadio.Miniproject.apis.jokes.repository.JokesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokesViewModel : ViewModel() {

    private val jokeRepository: JokesRepository by lazy { JokesRepository() }
    var jokeLiveData: LiveData<List<JokesUi>> =
        jokeRepository.selectAllJokes().map {
            it.toUi()
        }

    fun fetchNewJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            jokeRepository.fetchData()
        }
    }

    fun deleteJokeWithTimestamp(timestamp: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            jokeRepository.deleteJokeWithTimestamp(timestamp)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            jokeRepository.deleteAllJokes()
        }
    }
}

private fun List<JokesRoom>.toUi(): List<JokesUi> {
    return asSequence().map {
        JokesUi(
            category = it.category,
            setup = it.setup,
            delivery = it.delivery,
            api = it.api,
            timestamp = it.timestamp
        )
    }.toList()
}
