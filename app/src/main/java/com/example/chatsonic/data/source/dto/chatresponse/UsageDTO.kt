package com.example.chatsonic.data.source.dto.chatresponse

import com.example.chatsonic.domain.models.Usage

data class UsageDTO(
    val completion_tokens: Int,
    val prompt_tokens: Int,
    val total_tokens: Int
){
    fun toUsage(): Usage{
        return Usage(
            completion_tokens = completion_tokens,
            prompt_tokens = prompt_tokens,
            total_tokens = total_tokens
        )
    }
}