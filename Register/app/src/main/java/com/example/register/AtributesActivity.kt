package com.example.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_atributes.*

class AtributesActivity : AppCompatActivity() {

    lateinit var txtEducacion:EditText
    lateinit var txtOcupacion:EditText
    lateinit var txtDescripcion:EditText
    lateinit var  listaDisciplina:ArrayList<Disciplina>
    lateinit var  btnGuardar: Button

    //check box
    lateinit var  cbdis1 : CheckBox
    lateinit var  cbdis2 : CheckBox
    lateinit var  cbdis3 : CheckBox
    lateinit var  cbdis4 : CheckBox

    lateinit var uid:String
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atributes)

        initialize()



        initArrayDisciplina()
        cbdis1.setOnCheckedChangeListener{ _, _ ->
            when {
                cbdis1.isChecked -> {
                    this.listaDisciplina[0].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[0].seleccionado = false
                }
            }
        }

        cbdis2.setOnCheckedChangeListener{ _, _ ->
            when {
                cbdis2.isChecked -> {
                    this.listaDisciplina[1].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[1].seleccionado = false
                }
            }
        }

        cbdis3.setOnCheckedChangeListener{ _, _ ->
            when {
                cbdis3.isChecked -> {
                    this.listaDisciplina[2].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[2].seleccionado = false
                }
            }
        }

        cbdis4.setOnCheckedChangeListener{ _, _ ->
            when {
                cbdis4.isChecked -> {
                    this.listaDisciplina[3].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[3].seleccionado = false
                }
            }
        }


        guardar_tutor.setOnClickListener {
            // startActivity(Intent(this,MperfilActivity::class.java))
            guardar()
        }
    }


    private fun initArrayDisciplina()
    {
        this.listaDisciplina = ArrayList();
        this.listaDisciplina.add(Disciplina("1","cocina","",false))
        this.listaDisciplina.add(Disciplina("2","math","",false))
        this.listaDisciplina.add(Disciplina("3","ciencias","",false))
        this.listaDisciplina.add(Disciplina("4","arte","",false))
    }

    private fun initialize(){
        this.txtEducacion = findViewById(R.id.educacion_tutor)
        this.txtOcupacion = findViewById(R.id.ocupacion_tutor)
        this.txtDescripcion = findViewById(R.id.descripcion_tutor)
        this.cbdis1 = findViewById(R.id.disciplina_cocinar)
        this.cbdis2 = findViewById(R.id.disciplina_math)
        this.cbdis3 = findViewById(R.id.disciplina_ciencias)
        this.cbdis4 = findViewById(R.id.disciplina_arte)
        this.btnGuardar = findViewById(R.id.guardar_tutor)

        auth=FirebaseAuth.getInstance()
        val user: FirebaseUser?=auth.currentUser
        uid = user?.uid!!

        Log.d("Uid-Usuario", uid.toString())
        //this.txtEducacion.text = uid.toString()
    }


    private fun guardar()
    {
        val referencia = FirebaseDatabase.getInstance().getReference("Users").child(uid)
        referencia.child("nivel").setValue(txtEducacion.text.toString())
        referencia.child("ocupacion").setValue(txtOcupacion.text.toString())
        referencia.child("Descripcion").setValue(txtDescripcion.text.toString())
        referencia.child("disciplinas").setValue(listaDisciplina)

        Toast.makeText(this,"Guardado con exito",Toast.LENGTH_LONG).show()

    }








}
