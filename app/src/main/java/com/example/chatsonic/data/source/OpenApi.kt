package com.example.chatsonic.data.source

import com.example.chatsonic.data.source.dto.chatresponse.ChatResponseDTO
import com.example.chatsonic.data.source.dto.imageresponse.ImageResponseDTO
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenApi {

    @POST("/v1/completions")
    suspend fun generateChat(
        @Header("Content-Type") contentType: String,
        @Header("Authorization") authorization: String,
        @Body requestBody: RequestBody
    ): ChatResponseDTO

    @POST("/v1/images/generations")
    suspend fun generateImage(
        @Header("Content-Type") contentType: String,
        @Header("Authorization") authorization: String,
        @Body requestBody: RequestBody
    ): ImageResponseDTO
}