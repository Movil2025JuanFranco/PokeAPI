package net.iessochoa.sergiocontreras.pcdealguien

import android.app.Application
import net.iessochoa.sergiocontreras.pcdealguien.data.AppContainer
import net.iessochoa.sergiocontreras.pcdealguien.data.DefaultAppContainer


class PokemonApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}