package com.example.chatsonic.domain.models

data class ChatMessageModel(
    val isUser: Boolean = false,
    val isImage: Boolean = false,
    val userMessage: String
)
