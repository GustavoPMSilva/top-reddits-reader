package br.com.gustavopmsilva.topredditsreader.di

import br.com.gustavopmsilva.topredditsreader.ui.main.MainViewModel
import br.com.gustavopmsilva.topredditsreader.ui.main.PostAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel(get()) }
    single { PostAdapter() }
}
