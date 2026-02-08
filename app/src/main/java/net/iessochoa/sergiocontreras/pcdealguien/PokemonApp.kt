package net.iessochoa.sergiocontreras.pcdealguien

import net.iessochoa.sergiocontreras.pcdealguien.ui.PokemonScreen
import net.iessochoa.sergiocontreras.pcdealguien.ui.PokemonViewModel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PokemonApp( ) {
    val viewModel: PokemonViewModel =
        viewModel(factory = PokemonViewModel.Factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        PokemonScreen(
            uiState = uiState,
            onFetchClick = {viewModel.fetchPokemonByGeneration(uiState.selectedGeneration)},
            onGenerationSelection = {viewModel.selectGeneration(it)},
            modifier = Modifier.padding(innerPadding))

    }
}