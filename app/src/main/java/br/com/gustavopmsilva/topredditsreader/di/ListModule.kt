package br.com.gustavopmsilva.topredditsreader.di

import br.com.gustavopmsilva.topredditsreader.ui.list.ListViewModel
import br.com.gustavopmsilva.topredditsreader.ui.list.PostListAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listModule = module {
    viewModel { ListViewModel(get()) }
    single { (onClickListener: PostListAdapter.OnClickListener) -> PostListAdapter(onClickListener) }
}