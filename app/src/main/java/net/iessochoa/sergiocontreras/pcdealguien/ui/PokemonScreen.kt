package net.iessochoa.sergiocontreras.pcdealguien.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import net.iessochoa.sergiocontreras.pcdealguien.network.PokemonDto
import net.iessochoa.sergiocontreras.pcdealguien.ui.components.DynamicSelectTextField
import net.iessochoa.sergiocontreras.pcdealguien.ui.theme.PCdeAlguienTheme
import net.iessochoa.sergiocontreras.pcdealguien.ui.theme.Typography

@Composable
fun PokemonScreen(
    modifier: Modifier = Modifier,
    uiState: PokemonScreenUiState,
    onFetchClick: (Int) -> Unit,
    onGenerationSelection: (String) -> Unit,

) {

    val generationsCount = uiState.generations
    val selectedGen = uiState.selectedGeneration
    val generations =  (1..generationsCount).toList()

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Pokedex Retrofit", style = Typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        DynamicSelectTextField(
            selectedValue = selectedGen.toString(),
            options = generations.map { it.toString() },
            label = "Generación",
            onValueChangedEvent = onGenerationSelection
        )


        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                onFetchClick(selectedGen)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recuperar Pokémon")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (val status = uiState.requestStatus) {
            is RequestStatus.IsLoading -> CircularProgressIndicator()
            is RequestStatus.Error -> Text("Error")
            is RequestStatus.Success -> {
                LazyVerticalGrid(
                    modifier = Modifier.weight(1f),
                    columns = GridCells.Fixed(2), // 2 columnas
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(status.pokemonList) { pokemon ->
                        PokemonItem(pokemon)
                    }
                }
            }

            is RequestStatus.Idle -> Text("Selecciona una generación y pulsa buscar")
        }
    }
}

@Composable
fun PokemonItem(pokemon: PokemonDto) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp).fillMaxWidth()
        ) {
            val splittedUrl = pokemon.url.split("/")
            val id = splittedUrl[splittedUrl.lastIndex - 1]

            val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"

            AsyncImage(
                model = imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier.size(100.dp)
            )

            Text(
                text = pokemon.name.uppercase(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}