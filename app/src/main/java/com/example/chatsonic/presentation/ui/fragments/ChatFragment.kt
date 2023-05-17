package com.example.chatsonic.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatsonic.R
import com.example.chatsonic.databinding.FragmentChatBinding
import com.example.chatsonic.domain.models.ChatMessageModel
import com.example.chatsonic.domain.models.ChatRequest
import com.example.chatsonic.presentation.ui.adapters.ChatAdapter
import com.example.chatsonic.presentation.viewmodels.SendAndReceiveChatViewModel
import com.example.chatsonic.utils.Constants
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@AndroidEntryPoint
class ChatFragment : Fragment() {
    private lateinit var chatBinding: FragmentChatBinding
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var rcvLayoutManager: LinearLayoutManager
    private lateinit var contentType: String
    private lateinit var authorization: String
    private lateinit var requestBody: RequestBody
    private val sendAndReceiveChatViewModel by viewModels<SendAndReceiveChatViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        // Initializing Variables
        chatBinding = FragmentChatBinding.bind(view)
        chatAdapter = ChatAdapter()
        rcvLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
        rcvLayoutManager.stackFromEnd = true

        contentType = "application/json"
        authorization = "Bearer ${Constants.API_KEY}"

        requestBody = Gson().toJson(ChatRequest(
            max_tokens = 250,
            model = "text-davinci-003",
            prompt = chatBinding.inputText.text.toString(),
            temperature = 0.7
        )).toRequestBody("application/json".toMediaTypeOrNull())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatBinding.btnSend.setOnClickListener {
            if (chatBinding.inputText.text.toString().isEmpty()){
                Toast.makeText(requireContext(), "Please Enter a Message", Toast.LENGTH_SHORT).show()
            }
            else{
                callApi()
                setUpRecyclerView()
            }
        }
    }



    private fun callApi() {
        val chatList = ArrayList<ChatMessageModel>()
        chatList.add(ChatMessageModel(isUser = true, isImage = false, userMessage = chatBinding.inputText.text.toString()))
        chatAdapter.differ.submitList(chatList)

        sendAndReceiveChatViewModel.getChatResponse(contentType, authorization, requestBody)
        CoroutineScope(Dispatchers.IO).launch{
            sendAndReceiveChatViewModel.responseMessage.collect{
                when{
                    it.isLoading->{

                    }
                }
            }
        }

    }

    private fun setUpRecyclerView() {
        chatBinding.rcvChat.apply {
            adapter = chatAdapter
            layoutManager = rcvLayoutManager
            recycledViewPool.clear()
            smoothScrollToPosition(chatAdapter.differ.currentList.size-1)
        }
    }
}