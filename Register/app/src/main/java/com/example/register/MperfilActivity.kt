package com.example.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_mperfil.*

class MperfilActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var tvname : TextView
    private lateinit var tvemail : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mperfil)

        new_tutor.setOnClickListener {
            startActivity(Intent(this,TutorActivity::class.java))
        }
        miPerfil.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }

    }
}
