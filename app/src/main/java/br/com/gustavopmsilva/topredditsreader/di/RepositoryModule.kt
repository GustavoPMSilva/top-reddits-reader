package br.com.gustavopmsilva.topredditsreader.di

import br.com.gustavopmsilva.topredditsreader.data.repository.PostsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { PostsRepository(androidContext(), get(), get()) }
}
