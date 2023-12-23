package com.example.filmku.di

import com.squareup.moshi.Moshi
import com.example.filmku.BuildConfig
import com.example.filmku.network.ApiService
import com.example.filmku.network.interceptor.NoConnectionInterceptor
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModules = module {
    single { NoConnectionInterceptor(get()) }
    single { provideHttpLoggingInterceptor() }
    single { provideHttpClient(get(), get()) }
    single { createService<ApiService>(get(), get(named("BASE_URL"))) }
}

fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor, connectionInterceptor: NoConnectionInterceptor): OkHttpClient {
    val client = OkHttpClient.Builder().apply {
        connectTimeout(60, TimeUnit.SECONDS)
        writeTimeout(60, TimeUnit.SECONDS)
        readTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
        addInterceptor(connectionInterceptor)
        addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
            val request = requestBuilder
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
    }.build()

    return client
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

inline fun <reified T> createService(okHttpClient: OkHttpClient, baseUrl: String): T {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

    return retrofit.create(T::class.java)
}
