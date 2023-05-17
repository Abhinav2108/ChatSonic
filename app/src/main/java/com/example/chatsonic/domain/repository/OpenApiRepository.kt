package com.example.chatsonic.domain.repository

import com.example.chatsonic.data.source.dto.chatresponse.ChatResponseDTO
import com.example.chatsonic.data.source.dto.imageresponse.ImageResponseDTO
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header

interface OpenApiRepository {

    suspend fun generateChat(
        contentType: String,
        authorization: String,
        requestBody: RequestBody
    ): ChatResponseDTO

    suspend fun generateImage(
        contentType: String,
        authorization: String,
        requestBody: RequestBody
    ): ImageResponseDTO
}