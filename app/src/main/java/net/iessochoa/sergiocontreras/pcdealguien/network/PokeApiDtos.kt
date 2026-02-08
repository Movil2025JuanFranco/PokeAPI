package net.iessochoa.sergiocontreras.pcdealguien.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationsResponse(
    val results: List<GenerationDto>
)

@Serializable
data class GenerationDto(
    val name: String,
    val url: String
)

@Serializable
data class PokemonGenerationResponse(
    @SerialName("pokemon_species")
    val pokemons: List<PokemonDto>
)

@Serializable
data class PokemonDto(
    @SerialName("name")
    val name: String,

    @SerialName("url")
    val url: String
)