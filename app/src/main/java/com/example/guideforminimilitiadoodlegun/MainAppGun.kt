package com.example.guideforminimilitiadoodlegun

import android.app.Application
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module


class MainAppGun : Application() {
    override fun onCreate() {
        super.onCreate()
        // Creating an extended library configuration.
        val configGun =
            YandexMetricaConfig.newConfigBuilder("8fa7ab8f-dd06-4dd6-9a3a-4ad055fbe63f").build()
        // Initializing the AppMetrica SDK.
        YandexMetrica.activate(applicationContext, configGun)
        // Automatic tracking of user activity.
        YandexMetrica.enableActivityAutoTracking(this)
        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@MainAppGun)

            // load properties from assets/koin.properties file
            androidFileProperties()

            // module list
            modules(appModules)
        }
    }

    private val appModules: Module = module {
        viewModel {
            MuhamedSAVViewModel(this@MainAppGun)
        }
    }
}