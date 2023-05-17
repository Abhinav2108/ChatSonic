package com.example.chatsonic.presentation.ui.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.chatsonic.R
import com.example.chatsonic.domain.models.ChatMessageModel

class ChatAdapter: RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<ChatMessageModel>(){
        override fun areItemsTheSame(
            oldItem: ChatMessageModel,
            newItem: ChatMessageModel
        ): Boolean {
            return oldItem.userMessage == newItem.userMessage
        }

        override fun areContentsTheSame(
            oldItem: ChatMessageModel,
            newItem: ChatMessageModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        var view: View? = null
        if (viewType==0){
            view = LayoutInflater.from(parent.context).inflate(R.layout.user_chat, parent, false)
        }
        else{
            view = LayoutInflater.from(parent.context).inflate(R.layout.chatbot_chat, parent, false)
        }
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = differ.currentList[position]
        if (message.isUser==false){
            holder.container.visibility = View.GONE
        }
        if (message.isImage==true){
            holder.container.visibility = View.VISIBLE
            holder.image.load(message.userMessage)
        }else{
            holder.messageText.text = message.userMessage
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        val message = differ.currentList[position]
        if (message.isUser ==true){
            return 0
        }
        else{
            return 1
        }
    }

    inner class ChatViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val messageText = itemView.findViewById<TextView>(R.id.show_message)
        val container = itemView.findViewById<LinearLayout>(R.id.imageCard)
        val image = itemView.findViewById<ImageView>(R.id.image)
    }
}