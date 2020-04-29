package com.example.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_mostrar.*

class MostrarActivity : AppCompatActivity() {
    private lateinit var uid:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)
        var tutor = Model("Facebook", "Red social", R.drawable.ic_chef)
        var tutor1 = Model("WhatsApp", "Red social", R.drawable.ic_laptop)
        var tutor2 = Model("Instagram", "Red social", R.drawable.ic_art)

        var listaTutores = listOf(tutor,tutor1,tutor2)

        var adapter = TutorAdapter(this, listaTutores)
        list.adapter = adapter

        list.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, PseleccionadoActivity::class.java)
            intent.putExtra("tutor", listaTutores[position])
            startActivity(intent)
        }
    }
}


