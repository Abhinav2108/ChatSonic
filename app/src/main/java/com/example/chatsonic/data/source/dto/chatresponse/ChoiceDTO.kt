package com.example.chatsonic.data.source.dto.chatresponse

import com.example.chatsonic.domain.models.Choice

data class ChoiceDTO(
    val finish_reason: String,
    val index: Int,
    val logprobs: Any,
    val text: String
){
    fun toChoice(): Choice{
        return Choice(
            finish_reason = finish_reason,
            index = index,
            logprobs = logprobs,
            text = text
        )


    }
}