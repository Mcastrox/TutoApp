package com.example.tutoapp.Chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.example.tutoapp.R
import com.example.tutoapp.databinding.ActivityCentralChatBinding
import com.example.tutoapp.databinding.ActivityTutoriasBinding

class CentralChat : AppCompatActivity() {
    private lateinit var binding: ActivityCentralChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_central_chat)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_central_chat)
        setSupportActionBar(binding.toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mensajes_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.new_message -> {
                val intent= Intent (this, NewMessage::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}