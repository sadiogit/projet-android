package com.sadio.Miniproject.architecture

import com.sadio.Miniproject.apis.jokes.remote.JokesEndpoint
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val jokeRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://v2.jokeapi.dev/joke/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
        .build()

    fun getJoke(): JokesEndpoint = jokeRetrofit.create(JokesEndpoint::class.java)

}