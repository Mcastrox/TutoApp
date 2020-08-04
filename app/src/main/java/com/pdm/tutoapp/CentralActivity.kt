package com.pdm.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class CentralActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        logUserVerify()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_central)

        //BottomNavigationView con NavController
        bottomNavigationView = findViewById(R.id.bottomNavigation)
        navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)

    }

    private fun logUserVerify(){
        var uid = FirebaseAuth.getInstance().uid
        if(uid== null){
            var intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
