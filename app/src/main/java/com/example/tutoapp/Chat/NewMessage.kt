package com.example.tutoapp.Chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tutoapp.Model
import com.example.tutoapp.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.auth.User
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_message.*
import kotlinx.android.synthetic.main.user_row_new_message.view.*

class NewMessage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)
        fetchUsers()
    }
    private fun fetchUsers(){
        val ref= FirebaseDatabase.getInstance().getReference("Users")
        ref.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                p0.children.forEach{
                    val user= it.getValue(Model::class.java)
                    if(user!= null){
                        adapter.add(UserItem(user))
                    }


                }
                lista_usuarios.adapter=adapter
            }

        })
    }
    class UserItem (val user: Model): Item<ViewHolder>(){
        override fun getLayout(): Int {
            return R.layout.user_row_new_message
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.username_messages.text= user.name
            Picasso.get().load(user.ruta).into(viewHolder.itemView.img_chat)
        }

    }
}