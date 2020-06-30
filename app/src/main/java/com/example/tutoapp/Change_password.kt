package com.example.tutoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_register.*

class Change_password : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etchange_pass: EditText
    private lateinit var etnew_pass: EditText
    private lateinit var etconfirm_pass: EditText
    private var user: FirebaseUser? = null
    private var actual_pass : String = ""
    private var lista : ArrayList<String> = arrayListOf<String>()

    var toolbar : Toolbar? = null
    lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        etchange_pass = findViewById(R.id.pass_change)
        etnew_pass = findViewById(R.id.pass_new)
        etconfirm_pass = findViewById(R.id.confirm_pass)


        modificar_info.setOnClickListener {
            update()
        }

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.change_password)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser
        uid = user?.uid!!

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val userRef = ref.child(user?.uid!!)


        userRef.addValueEventListener (object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
               if(dataSnapshot.child("pass").exists()){
                   lista.add(dataSnapshot.child("pass").value as String)
               }
            }
        })

        Log.d("1", lista[0])

    }

    private fun update() {




        val password: String = etchange_pass.text.toString()
        val new: String = etnew_pass.text.toString()
        val confirm: String = etconfirm_pass.text.toString()


        if (etnew_pass.text.length < 6 || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm) || (new != confirm) ) {
            etnew_pass.setError("La contraseña debe tener al menos 6 caracteres")
            etchange_pass.setError("No puede ir vacío.")
            etconfirm_pass.setError("Debe ser igual a la nueva contraseña")

        }

        val newPassword = etnew_pass.text.toString()


        if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(new) && (new == confirm) && (etnew_pass.text.length >= 6))
            user?.updatePassword(newPassword)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password actualizado", Toast.LENGTH_LONG).show()

                        etchange_pass.text.clear()
                        etnew_pass.text.clear()
                        etconfirm_pass.text.clear()
                    }
                }
        else {
            Toast.makeText(this, "Por favor revise todos los campos", Toast.LENGTH_LONG).show()
        }

    }

}


