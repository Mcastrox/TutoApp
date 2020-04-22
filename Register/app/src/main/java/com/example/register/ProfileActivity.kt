package com.example.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var tvname : TextView
    private lateinit var tvemail : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initialise()
        /*val user: FirebaseUser?=mAuth.currentUser
        mDataBaseReference= FirebaseDatabase.getInstance().getReference("Users")
        uid = user?.uid!!
        val mUser= mAuth!!.currentUser
        val mUserReference= mDataBaseReference!!.child(mUser!!.uid)*/
    }


    private fun initialise()
    {
        auth = FirebaseAuth.getInstance()
        val user:FirebaseUser?=auth.currentUser
        //para los textos
        tvname = findViewById(R.id.tvusername)
        tvemail = findViewById(R.id.tvemail)


        tvemail.text = user?.email!!.toString()


        //buscando el nombre
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val userRef = ref.child(user?.uid!!)

        // tvname.text = user?.uid!!.toString()
        userRef.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(dataSnapshot: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // sustituir nombre por "Name"
                tvname.text = dataSnapshot.child("Name").value as String

            }

        })

    }



}
