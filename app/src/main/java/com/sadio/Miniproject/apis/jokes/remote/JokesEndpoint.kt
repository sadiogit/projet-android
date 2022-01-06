package com.sadio.Miniproject.apis.jokes.remote

import com.sadio.Miniproject.apis.jokes.model.JokesRetrofit
import retrofit2.http.GET

interface JokesEndpoint {

    //@GET("Any")
    @GET("Any?type=twopart")
    suspend fun getRandomJoke() : JokesRetrofit
}