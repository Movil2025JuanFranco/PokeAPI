package net.iessochoa.sergiocontreras.pcdealguien.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.pcdealguien.PokemonApplication
import net.iessochoa.sergiocontreras.pcdealguien.data.PokemonRepository
import okhttp3.WebSocket

class PokemonViewModel(
    private  val pokemonRepository: PokemonRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(PokemonScreenUiState(
        requestStatus = RequestStatus.IsLoading
    ))
    val uiState: StateFlow<PokemonScreenUiState> = _uiState.asStateFlow()

    init {
        getGenerations()
    }

    private fun getGenerations() {
        viewModelScope.launch {
            try {
                val generationList = pokemonRepository.getGenerations()
                _uiState.update { currentState ->
                    val generationsCount = generationList.count()
                    currentState.copy(
                        generations = generationsCount,
                        requestStatus = RequestStatus.Idle
                    )
                }
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(requestStatus = RequestStatus.Error)
                }
            }
        }
    }

    fun selectGeneration(generation: String) {
        _uiState.update { currentState ->
            currentState.copy(selectedGeneration = generation.toIntOrNull() ?: 1)
        }
    }

    fun fetchPokemonByGeneration(generationId: Int) {

        viewModelScope.launch {
            try {
                _uiState.update { currentState ->
                    currentState.copy(
                       requestStatus = RequestStatus.IsLoading
                    )
                }

                val response = pokemonRepository.getGenerationPokemons(generationId)
                _uiState.update { currentState ->
                    currentState.copy(
                        requestStatus = RequestStatus.Success(response)
                    )
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PokemonApplication)
                val pokemonRepository = application.container.pokemonRepository
                PokemonViewModel(pokemonRepository = pokemonRepository)
            }
        }
    }
}