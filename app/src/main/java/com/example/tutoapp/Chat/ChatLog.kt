package com.example.tutoapp.Chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tutoapp.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*

class ChatLog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)
        /*val userName = intent.getStringExtra("nombre")
        val uriPhotoUser = intent.getStringExtra("uri")
        val idUserto = intent.getStringExtra("userId")

        supportActionBar?.title = userName*/

        val adapter = GroupAdapter<ViewHolder>()
        adapter.add(ChatToItem("Este es un mensaje enviado"))
        chat_log.adapter = adapter

    }

    class ChatFromItem (val text : String): Item<ViewHolder>() {
        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.txt_msg_recibido.text=text

        }

        override fun getLayout(): Int {
            return R.layout.chat_from_row
        }

    }

    class ChatToItem (val text: String): Item<ViewHolder>() {
        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.txt_msg_enviado.text=text

        }

        override fun getLayout(): Int {
            return R.layout.chat_to_row
        }

    }
}