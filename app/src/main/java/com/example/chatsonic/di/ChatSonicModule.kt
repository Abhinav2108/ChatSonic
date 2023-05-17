package com.example.chatsonic.di

import com.example.chatsonic.data.repository.OpenApiRepositoryImpl
import com.example.chatsonic.data.source.OpenApi
import com.example.chatsonic.domain.repository.OpenApiRepository
import com.example.chatsonic.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object ChatSonicModule {
    @Provides
    @Singleton
    fun provideOpenApi(): OpenApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOpenApiRepository(openApi: OpenApi):OpenApiRepository{
        return OpenApiRepositoryImpl(openApi)
    }


}