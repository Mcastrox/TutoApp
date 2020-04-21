package com.example.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class Register_Activity : AppCompatActivity() {
    private lateinit var txtName: EditText
    private lateinit var txtLastname: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        txtName=findViewById(R.id.txt_name)
        txtLastname=findViewById(R.id.txt_lastname)
        txtEmail=findViewById(R.id.txt_email)
        txtPassword=findViewById(R.id.txt_password)

        progressBar= this.findViewById(R.id.progressBar)

        database= FirebaseDatabase.getInstance()
        auth=FirebaseAuth.getInstance()
        dbReference=database.reference.child("Users")

    }
    fun register(view:View){
        createNewUser()
        // startActivity(Intent(this,MainActivity::class.java))

    }
    private fun createNewUser(){
        val name: String = txt_name.text.toString()
        val lastName: String = txt_lastname.text.toString()
        val email: String = txt_email.text.toString()
        val pass: String = txt_password.text.toString()

        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(lastName)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(pass)){
            progressBar.visibility=View.VISIBLE
            auth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this){
                        task->
                    if(task.isComplete){
                        val user:FirebaseUser?=auth.currentUser
                        verifyEmail((user))

                        val userBD=dbReference.child(user?.uid!!)
                        userBD.child("Name").setValue(name)
                        userBD.child("lastName").setValue(lastName)
                        action()
                    }
                }
        }
    }
    private fun action (){
        startActivity(Intent(this,MainActivity::class.java))

    }


    private fun verifyEmail(user:FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){
                    task ->
                if(task.isComplete){
                    Toast.makeText(this,"Email enviado con exito",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"error al enviar",Toast.LENGTH_LONG).show()
                }
            }
    }
}