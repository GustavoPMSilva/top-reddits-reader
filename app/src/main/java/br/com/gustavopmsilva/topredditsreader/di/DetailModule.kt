package br.com.gustavopmsilva.topredditsreader.di

import br.com.gustavopmsilva.topredditsreader.data.domain.Post
import br.com.gustavopmsilva.topredditsreader.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    viewModel { (post: Post) -> DetailViewModel(post) }
}
