package br.com.gustavopmsilva.topredditsreader

import android.app.Application
import br.com.gustavopmsilva.topredditsreader.di.mainModule
import br.com.gustavopmsilva.topredditsreader.di.networkModule
import br.com.gustavopmsilva.topredditsreader.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(mainModule, networkModule, repositoryModule))
        }
    }
}
