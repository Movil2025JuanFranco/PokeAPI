package net.iessochoa.sergiocontreras.pcdealguien.ui

import net.iessochoa.sergiocontreras.pcdealguien.network.GenerationDto
import net.iessochoa.sergiocontreras.pcdealguien.network.PokemonDto

data class PokemonScreenUiState (
    val selectedGeneration: Int = 1,
    val generations: Int = 1,
    val requestStatus: RequestStatus = RequestStatus.Idle

)

sealed interface RequestStatus{
    object IsLoading: RequestStatus
    data class Success(val pokemonList: List<PokemonDto>): RequestStatus
    object Error : RequestStatus
    object Idle: RequestStatus
}

data class OldPokemonUiState(
    val generations: Int = 1,
    val pokemons: List<PokemonDto>,
    val isError: Boolean = false,
    val isLoading: Boolean = true,
    val selectedGeneration: Int = 1
)