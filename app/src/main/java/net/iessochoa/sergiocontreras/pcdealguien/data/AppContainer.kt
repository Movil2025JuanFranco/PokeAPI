package net.iessochoa.sergiocontreras.pcdealguien.data

import kotlinx.serialization.json.Json
import net.iessochoa.sergiocontreras.pcdealguien.network.PokemonApiService
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlin.getValue


interface AppContainer {
    val pokemonRepository: PokemonRepository
}

class DefaultAppContainer: AppContainer {
    private val baseUrl = "https://pokeapi.co/api/v2/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(baseUrl)
            .build()
    }

    private val retrofitService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }

    override val pokemonRepository: PokemonRepository by lazy {
        NetworkPokemonRepository(retrofitService)

    }
}