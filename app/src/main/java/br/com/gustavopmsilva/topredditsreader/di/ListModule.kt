package br.com.gustavopmsilva.topredditsreader.di

import br.com.gustavopmsilva.topredditsreader.ui.list.ListPostAdapter
import br.com.gustavopmsilva.topredditsreader.ui.list.ListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listModule = module {
    viewModel { ListViewModel(get()) }
    single { (onClickListener: ListPostAdapter.OnClickListener) -> ListPostAdapter(onClickListener) }
}
