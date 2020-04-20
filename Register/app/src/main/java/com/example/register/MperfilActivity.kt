package com.example.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mperfil.*

class MperfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mperfil)

        new_tutor.setOnClickListener {
            startActivity(Intent(this,TutorActivity::class.java))
        }
    }

}
