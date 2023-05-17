package com.example.chatsonic.domain.models

import com.example.chatsonic.data.source.dto.chatresponse.ChoiceDTO
import com.example.chatsonic.data.source.dto.chatresponse.UsageDTO

data class ChatResponse(
    val choice: List<Choice>,
    val created: Int,
    val id: String,
    val model: String,
    val `object`: String,
    val usageDTO: Usage
)
