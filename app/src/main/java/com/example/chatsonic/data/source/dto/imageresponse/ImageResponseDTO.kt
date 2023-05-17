package com.example.chatsonic.data.source.dto.imageresponse

import com.example.chatsonic.domain.models.ImageResponse

data class ImageResponseDTO(
    val created: Int,
    val `data`: List<DataDTO>
){
    fun toImageResponse(): ImageResponse{
        return ImageResponse(
            created = created,
            `data` = `data`.map { it.toData() }
        )
    }
}