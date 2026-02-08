# 🖥️ PC de Alguien (Someone's PC)

> *"¿Acceder al PC de ALGUIEN?" ... "Sistema de Almacenamiento Pokémon activado."*

Bienvenido a **PC de Alguien**. Este proyecto es una práctica de desarrollo Android (PMDM) diseñada para aprender a consumir APIs REST y gestionar listas de datos e imágenes de forma eficiente.

El nombre rinde homenaje a los juegos originales de **Pokémon Rojo y Azul**. Antes de conocer a **Bill** (el programador del sistema) en su casa de la Ruta 25, el ordenador de los Centros Pokémon se llamaba misteriosamente "PC de Alguien". Al igual que Bill tuvo que programar el sistema, ¡ahora te toca a ti!

## 🎯 Objetivo de la Práctica

El objetivo principal es familiarizarse con la librería **Retrofit** para llamadas de red y **Coil** para la carga de imágenes, integrándolo todo en una interfaz moderna con **Jetpack Compose**.

La app permite:
1.  Seleccionar una **Generación** de Pokémon (Kanto, Johto, Hoenn...).
2.  Consultar la **PokeApi** para obtener la lista de especies.
3.  Mostrar los resultados en una rejilla (`LazyVerticalGrid`) con su imagen y nombre.

## 🛠️ Tech Stack & Librerías

El proyecto está construido utilizando las últimas prácticas recomendadas para el desarrollo educativo en Android:

* **Lenguaje:** [Kotlin](https://kotlinlang.org/)
* **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material Design 3)
* **Arquitectura:** MVVM (Model-View-ViewModel) con gestión de estado (`UiState`).
* **Red (Networking):**
    * [Retrofit 3.0.0](https://square.github.io/retrofit/) - Cliente HTTP.
    * [Gson](https://github.com/google/gson) - Convertidor de JSON.
* **Imágenes:**
    * [Coil 2.7.0](https://coil-kt.github.io/coil/) - Carga asíncrona de imágenes para Compose.
* **Concurrencia:** Kotlin Coroutines & Flow.

## 🧩 Estructura del Proyecto

El código sigue una estructura limpia para facilitar el mantenimiento:

```text
com.example.pokepractice
├── data/                  # Capa de Datos
│   ├── network/           # Configuración de Retrofit (ApiService, Client)
│   └── PokemonModels.kt   # Data classes (Modelos de respuesta API)
├── ui/                    # Capa de UI (Presentación)
│   ├── PokemonScreen.kt   # Composable principal con la UI
│   ├── PokemonViewModel.kt# Lógica de negocio y llamadas asíncronas
│   └── PokemonUiState.kt  # Estado de la pantalla (Loading, List, Error)
└── MainActivity.kt        # Punto de entrada