package com.example.sw_josemanuelcanete

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getPersonaje(@Url url:String): Response<PersonajeResponse>
}