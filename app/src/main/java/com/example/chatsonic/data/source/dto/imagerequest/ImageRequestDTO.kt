package com.example.chatsonic.data.source.dto.imagerequest

import com.example.chatsonic.domain.models.ImageRequest

data class ImageRequestDTO(
    val n: Int,
    val prompt: String,
    val size: String
){
    fun toImageRequest(): ImageRequest{
        return ImageRequest(
            n,
            prompt,
            size
        )
    }
}