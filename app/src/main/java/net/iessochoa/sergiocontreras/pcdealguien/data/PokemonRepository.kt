package net.iessochoa.sergiocontreras.pcdealguien.data

import net.iessochoa.sergiocontreras.pcdealguien.network.GenerationDto
import net.iessochoa.sergiocontreras.pcdealguien.network.PokemonApiService
import net.iessochoa.sergiocontreras.pcdealguien.network.PokemonDto

interface PokemonRepository{
    suspend fun getGenerationPokemons(generation: Int): List<PokemonDto>
    suspend fun getGenerations(): List<GenerationDto>
}

class NetworkPokemonRepository(
    private val apiService: PokemonApiService
): PokemonRepository {
    override suspend fun getGenerationPokemons(generation: Int): List<PokemonDto> {
        return apiService.getGenerationPokemons(generation).pokemons
    }

    override suspend fun getGenerations(): List<GenerationDto> {
        return apiService.getGenerations().results
    }


}