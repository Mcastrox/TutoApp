package com.example.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.register.R.id.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_.*
import kotlinx.android.synthetic.main.activity_register.*

class Login_Activity : AppCompatActivity() {
    private lateinit var txtUser:EditText
    private lateinit var txtPassword:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)
        txtUser=findViewById(R.id.txtUser)
        txtPassword=findViewById(R.id.txtPassword)
        progressBar= findViewById(R.id.progressBar2)
        auth= FirebaseAuth.getInstance()

    }
    fun forgotPassword(view:View){
        startActivity(Intent(this,Forgotpass_Activity::class.java))
    }
    fun register(view:View){
        startActivity(Intent(this,Register_Activity::class.java))
    }
    fun login (view: View){
        loginUser()
    }
    private fun loginUser(){
        val user: String=txtUser.text.toString()
        val pass: String=txtPassword.text.toString()
        if(!TextUtils.isEmpty(user)&&!TextUtils.isEmpty(pass) ){
            progressBar.visibility=View.VISIBLE

            auth.signInWithEmailAndPassword(user,pass)
                .addOnCompleteListener(this){
                        task->
                    if(task.isSuccessful){
                        action()
                    }else{
                        Toast.makeText(this,"Error al autenticar", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
    private fun action(){
        startActivity(Intent(this,MainActivity::class.java))
    }
}
