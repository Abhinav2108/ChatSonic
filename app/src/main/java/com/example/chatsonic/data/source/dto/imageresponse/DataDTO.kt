package com.example.chatsonic.data.source.dto.imageresponse

import com.example.chatsonic.domain.models.Data

data class DataDTO(
    val url: String
){
    fun toData(): Data {
        return Data(
            url = url
        )
    }
}