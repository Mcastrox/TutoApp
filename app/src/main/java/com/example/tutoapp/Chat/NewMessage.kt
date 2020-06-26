package com.example.tutoapp.Chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
                p0.children.forEach() { e ->
                    val user: Model
                    var lastName: String = ""
                    var email: String = ""
                    var nivel: String = ""
                    var ocupation: String = ""
                    var cellphone: String = ""
                    var direccion: String = ""
                    var rol: String = ""
                    var name: String = ""
                    var ruta: String = ""
                    var descripcion: String = ""
                    var id: String = e.child("ID").value as String

                    if (e.child("Name").value != null) {
                        name = e.child("Name").value as String
                    }
                    if (e.child("lastName").value != null) {
                        lastName = e.child("lastName").value as String
                    }
                    if (e.child("direccion").value != null) {
                        direccion = e.child("direccion").value as String
                    }
                    if (e.child("correo").value != null) {
                        email = e.child("correo").value as String
                    }
                    if (e.child("telefono").value != null) {
                        cellphone = e.child("telefono").value as String
                    }
                    if (e.child("nivel").value != null) {
                        nivel = e.child("nivel").value as String
                    }
                    if (e.child("ocupacion").value != null) {
                        ocupation = e.child("ocupacion").value as String
                    }
                    if (e.child("Rol").value != null) {
                        rol = e.child("Rol").value as String
                    }
                    if (e.child("Descripcion").value != null) {
                        descripcion = e.child("Descripcion").value as String
                    }
                    if (e.child("urlImage").value != null) {
                        ruta = e.child("urlImage").value as String
                    }

                    user = Model(id,name,lastName, email, cellphone, nivel, ocupation, direccion, R.drawable.ic_art, ruta, descripcion, null )
                    adapter.add(UserItem(user))
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
            Log.d("ruta",user.name)
            Picasso.get().load(user.ruta).into(viewHolder.itemView.img_chat)
        }

    }
}