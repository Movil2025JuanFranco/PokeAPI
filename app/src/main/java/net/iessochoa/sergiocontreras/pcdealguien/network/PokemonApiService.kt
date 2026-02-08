package net.iessochoa.sergiocontreras.pcdealguien.network

import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonApiService {
    @GET("generation")
    suspend fun getGenerations(): GenerationsResponse

    @GET("generation/{id}")
    suspend fun getGenerationPokemons(@Path("id")id: Int): PokemonGenerationResponse
}