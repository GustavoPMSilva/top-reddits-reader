package br.com.gustavopmsilva.topredditsreader.core.extension

import br.com.gustavopmsilva.topredditsreader.core.base.Resource
import kotlinx.coroutines.flow.*
import org.koin.core.scope.Scope
import retrofit2.Retrofit
import java.lang.Exception

inline fun <reified T> Scope.resolveRetrofit(): T {
    val retrofit: Retrofit = get()
    return retrofit.create(T::class.java)
}

fun <T> Flow<T>.toFlowResource(): Flow<Resource<T>> = flow {
    onStart { emit(Resource.Loading<T>()) }
        .onEach { emit(Resource.Success(it)) }
        .catch { emit(Resource.Error<T>(Exception(it))) }
        .collect()
}
