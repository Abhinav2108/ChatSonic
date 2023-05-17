package com.example.chatsonic.data.repository

import com.example.chatsonic.data.source.OpenApi
import com.example.chatsonic.data.source.dto.chatresponse.ChatResponseDTO
import com.example.chatsonic.data.source.dto.imageresponse.ImageResponseDTO
import com.example.chatsonic.domain.repository.OpenApiRepository
import okhttp3.RequestBody
import javax.inject.Inject

class OpenApiRepositoryImpl @Inject constructor(private val openApi: OpenApi): OpenApiRepository {
    override suspend fun generateChat(
        contentType: String,
        authorization: String,
        requestBody: RequestBody
    ): ChatResponseDTO {
        return openApi.generateChat(contentType, authorization, requestBody)
    }

    override suspend fun generateImage(
        contentType: String,
        authorization: String,
        requestBody: RequestBody
    ): ImageResponseDTO {
        return openApi.generateImage(contentType, authorization, requestBody)
    }
}