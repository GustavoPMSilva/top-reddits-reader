package br.com.gustavopmsilva.topredditsreader.di

import br.com.gustavopmsilva.topredditsreader.core.extension.resolveRetrofit
import br.com.gustavopmsilva.topredditsreader.data.api.PostsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://www.reddit.com/"

val networkModule = module {
    single { getOkHttpClient() }
    single { getMoshi() }
    single { getRetrofit(get(), get()) }
    single<PostsApi> { resolveRetrofit() }
}

private fun getRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}

private fun getMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

private fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }.build()
}
