package com.example.chatsonic.domain.usecases

import com.example.chatsonic.domain.models.ChatResponse
import com.example.chatsonic.domain.repository.OpenApiRepository
import com.example.chatsonic.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SendAndReceiveChatUseCase @Inject constructor(private val repository: OpenApiRepository) {
    operator fun invoke(
        contentType: String,
        authorization: String,
        requestBody: RequestBody
    ): Flow<ResponseState<ChatResponse>> = flow {
        try {
            emit(ResponseState.Loading())
            val request = repository.generateChat(contentType, authorization, requestBody)
            val response = request.toChatResponse()
            emit(ResponseState.Success(data = response))
        }
        catch (e: HttpException){
            emit(ResponseState.Error(message = "${e.localizedMessage}"))
        }
        catch (e: IOException){
            emit(ResponseState.Error(message = "Input Output Exception Occurred"))
        }

    }
}