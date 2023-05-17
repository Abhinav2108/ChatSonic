package com.example.chatsonic.presentation.states

import com.example.chatsonic.domain.models.ChatResponse

data class ChatState(
    val isLoading: Boolean = false,
    val responseMessage: ChatResponse? = null,
    val errorMessage: String? = null
)
