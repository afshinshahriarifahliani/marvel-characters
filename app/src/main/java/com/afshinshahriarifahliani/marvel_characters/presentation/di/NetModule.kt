package com.afshinshahriarifahliani.marvel_characters.presentation.di

import com.afshinshahriarifahliani.marvel_characters.BuildConfig
import com.afshinshahriarifahliani.marvel_characters.data.api.MarvelApiService
import com.afshinshahriarifahliani.marvel_characters.util.digest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Timestamp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        //ts - a timestamp (or other long string which can change on a request-by-request basis)
        val ts = Timestamp(System.currentTimeMillis()).toString()

        // Add a logging interceptor to monitor and modify the Api requests
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .addInterceptor { chain ->
                    val theOriginals = chain.request()
                    val originalHttpUrl = theOriginals.url

                    /** Server-side applications must pass two parameters in addition to the apikey parameter
                     * so the original url is modified in here to include those parameters
                     */
                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("ts", ts)
                        .addQueryParameter("apikey", BuildConfig.API_KEY)
                        .addQueryParameter(
                            "hash",
                            digest(ts, BuildConfig.API_KEY, BuildConfig.PRI_KEY)
                        )
                        .build()
                    chain.proceed(theOriginals.newBuilder().url(url).build())
                }

        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMarvelApiService(retrofit: Retrofit): MarvelApiService {
        return retrofit.create(MarvelApiService::class.java)
    }
}