package com.example.chatsonic.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatsonic.domain.usecases.SendAndReceiveChatUseCase
import com.example.chatsonic.presentation.states.ChatState
import com.example.chatsonic.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class SendAndReceiveChatViewModel @Inject constructor(private val sendAndReceiveChatUseCase: SendAndReceiveChatUseCase): ViewModel(){

    private val _responseMessage = MutableStateFlow(ChatState())
    val responseMessage: StateFlow<ChatState> = _responseMessage

    fun getChatResponse(
        contentType: String,
        authorization: String,
        requestBody: RequestBody
    ) = viewModelScope.launch(Dispatchers.IO) {
        sendAndReceiveChatUseCase(contentType, authorization, requestBody).collect{
            when(it){
                is ResponseState.Loading->{
                    _responseMessage.value = ChatState(isLoading = true)
                }
                is ResponseState.Success->{
                    _responseMessage.value = ChatState(responseMessage = it.data)
                }
                is ResponseState.Error->{
                    _responseMessage.value = ChatState(errorMessage = it.message.toString())
                }
            }
        }
    }
}