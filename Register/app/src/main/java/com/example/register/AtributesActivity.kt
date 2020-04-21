package com.example.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_atributes.*

class AtributesActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var educacion_tutor: EditText
    private lateinit var ocupacion_tutor: EditText
    private lateinit var descripcion_tutor:EditText
    private lateinit var disciplinas_tutor: ArrayList<Disciplina>
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    lateinit var uid: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atributes)
        initialize()
        initDisciplinas()
        disciplina_cocinar.setOnCheckedChangeListener{ _, _ ->
            when {
                disciplina_cocinar.isChecked -> {
                    this.disciplinas_tutor[0].seleccionado = true
                }
                else -> {
                    this.disciplinas_tutor[0].seleccionado = false
                }
            }
        }

        disciplina_math.setOnCheckedChangeListener{ _, _ ->
            when {
                disciplina_math.isChecked -> {
                    this.disciplinas_tutor[1].seleccionado = true
                }
                else -> {
                    this.disciplinas_tutor[1].seleccionado = false
                }
            }
        }

        disciplina_ciencias.setOnCheckedChangeListener{ _, _ ->
            when {
                disciplina_ciencias.isChecked -> {
                    this.disciplinas_tutor[2].seleccionado = true
                }
                else -> {
                    this.disciplinas_tutor[2].seleccionado = false
                }
            }
        }

        disciplina_arte.setOnCheckedChangeListener{ _, _ ->
            when {
                disciplina_arte.isChecked -> {
                    this.disciplinas_tutor[3].seleccionado = true
                }
                else -> {
                    this.disciplinas_tutor[3].seleccionado = false
                }
            }
        }

        guardar_tutor.setOnClickListener {
            guardarDatos()
            //startActivity(Intent(this,MperfilActivity::class.java))
        }

    }


    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.disciplina_cocinar -> {
                    if (checked) {
                        this.disciplinas_tutor[0].seleccionado = true
                    } else {
                        this.disciplinas_tutor[0].seleccionado = false
                    }
                }
                R.id.disciplina_math -> {
                    if (checked) {
                        this.disciplinas_tutor[1].seleccionado = true
                    } else {
                        this.disciplinas_tutor[1].seleccionado = false
                    }
                }
                R.id.disciplina_ciencias -> {
                    if (checked) {
                        this.disciplinas_tutor[2].seleccionado = true
                    } else {
                        this.disciplinas_tutor[2].seleccionado = false
                    }
                }
                R.id.disciplina_arte -> {
                    if (checked) {
                        this.disciplinas_tutor[3].seleccionado = true
                    } else {
                        this.disciplinas_tutor[3].seleccionado = false
                    }
                }
                // TODO: Veggie sandwich
            }
        }
    }
    private fun initialize(){
        auth= FirebaseAuth.getInstance()
        educacion_tutor=findViewById(R.id.educacion_tutor)
        ocupacion_tutor=findViewById(R.id.ocupacion_tutor)
        descripcion_tutor=findViewById(R.id.descripcion_tutor)
        dbReference=database.reference.child("Users")
        val user: FirebaseUser?=auth.currentUser
        uid=user?.uid!!
        Log.d("Uid Usuario", uid.toString())

    }
    private fun initDisciplinas(){
        this.disciplinas_tutor=arrayListOf()
        this.disciplinas_tutor.add(Disciplina("1","Cocina","Saber cocinar como negro"))
        this.disciplinas_tutor.add(Disciplina("2","Mate","saber sumar "))
        this.disciplinas_tutor.add(Disciplina("3","Ciencias","yeah science bitch"))
        this.disciplinas_tutor.add(Disciplina("4","Arte","no ser daltonico"))
    }
    private fun guardarDatos(){
        val referencia= FirebaseDatabase.getInstance().getReference("User").child(uid)
        referencia.child("nivel").setValue(educacion_tutor.text.toString())
        referencia.child("ocupacion").setValue(ocupacion_tutor.text.toString())
        referencia.child("descripcion").setValue(descripcion_tutor.text.toString())
        referencia.child("disciplinas").setValue(disciplinas_tutor)
        Toast.makeText(this,"Guardado con exito",Toast.LENGTH_LONG).show()
    }
}
