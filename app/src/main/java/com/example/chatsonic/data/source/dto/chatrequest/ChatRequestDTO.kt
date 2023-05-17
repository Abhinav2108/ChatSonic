package com.example.chatsonic.data.source.dto.chatrequest

import com.example.chatsonic.domain.models.ChatRequest

data class ChatRequestDTO(
    val max_tokens: Int,
    val model: String,
    val prompt: String,
    val temperature: Double
){
    fun toChatRequest(): ChatRequest{
        return ChatRequest(
            max_tokens = max_tokens,
            model = model,
            prompt = prompt,
            temperature = temperature
        )
    }
}