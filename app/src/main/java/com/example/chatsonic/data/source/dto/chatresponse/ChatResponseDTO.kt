package com.example.chatsonic.data.source.dto.chatresponse

import com.example.chatsonic.domain.models.ChatResponse

data class ChatResponseDTO(
    val choice: List<ChoiceDTO>,
    val created: Int,
    val id: String,
    val model: String,
    val `object`: String,
    val usageDTO: UsageDTO
) {
    fun toChatResponse(): ChatResponse{
        return ChatResponse(
            choice = choice.map { it.toChoice() },
            created = created,
            id = id,
            model = model,
            `object` = `object`,
            usageDTO = usageDTO.toUsage()
        )
    }
}