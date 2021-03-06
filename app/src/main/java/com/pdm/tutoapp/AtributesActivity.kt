package com.pdm.tutoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.pdm.tutoapp.components.MoneyFilter
import com.pdm.tutoapp.models.Disciplina
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_atributes.*

class AtributesActivity : AppCompatActivity() {

    private lateinit var txtEducacion: EditText
    private lateinit var txtOcupacion: EditText
    private lateinit var txtDescripcion: EditText
    private lateinit var listaDisciplina: ArrayList<Disciplina>
    private lateinit var btnGuardar: Button
    private lateinit var etCuota: EditText


    //check box
    private lateinit var cbdis1: CheckBox
    private lateinit var cbdis2: CheckBox
    private lateinit var cbdis3: CheckBox
    private lateinit var cbdis4: CheckBox
    private lateinit var cbdis5: CheckBox
    private lateinit var cbdis6: CheckBox
    private lateinit var cbdis7: CheckBox
    private lateinit var cbdis8: CheckBox
    private lateinit var cbdis9: CheckBox
    private lateinit var cbdis10: CheckBox
    private lateinit var cbdis11: CheckBox
    private lateinit var cbdis12: CheckBox


    private lateinit var uid: String
    private lateinit var auth: FirebaseAuth

    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atributes)

        initArrayDisciplina()

        initialize()

        cbdis1.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis1.isChecked -> {
                    this.listaDisciplina[0].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[0].seleccionado = false
                }
            }
        }
        cbdis2.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis2.isChecked -> {
                    this.listaDisciplina[1].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[1].seleccionado = false
                }
            }
        }
        cbdis3.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis3.isChecked -> {
                    this.listaDisciplina[2].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[2].seleccionado = false
                }
            }
        }
        cbdis4.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis4.isChecked -> {
                    this.listaDisciplina[3].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[3].seleccionado = false
                }
            }
        }
        cbdis5.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis5.isChecked -> {
                    this.listaDisciplina[4].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[4].seleccionado = false
                }
            }
        }
        cbdis6.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis6.isChecked -> {
                    this.listaDisciplina[5].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[5].seleccionado = false
                }
            }
        }
        cbdis7.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis7.isChecked -> {
                    this.listaDisciplina[6].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[6].seleccionado = false
                }
            }
        }
        cbdis8.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis8.isChecked -> {
                    this.listaDisciplina[7].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[7].seleccionado = false
                }
            }
        }
        cbdis9.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis9.isChecked -> {
                    this.listaDisciplina[8].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[8].seleccionado = false
                }
            }
        }
        cbdis10.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis10.isChecked -> {
                    this.listaDisciplina[9].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[9].seleccionado = false
                }
            }
        }
        cbdis11.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis11.isChecked -> {
                    this.listaDisciplina[10].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[10].seleccionado = false
                }
            }
        }
        cbdis12.setOnCheckedChangeListener { _, _ ->
            when {
                cbdis12.isChecked -> {
                    this.listaDisciplina[11].seleccionado = true
                }
                else -> {
                    this.listaDisciplina[11].seleccionado = false
                }
            }
        }

        guardar_tutor.setOnClickListener {
            guardar()
        }

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.tutor_profile)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

    }


    private fun initArrayDisciplina() {
        this.listaDisciplina = ArrayList();
        this.listaDisciplina.add(
            Disciplina(
                "1",
                "Arte",
                "",
                false
            )
        )
        this.listaDisciplina.add(
            Disciplina(
                "2",
                "Idiomas",
                "",
                false
            )
        )
        this.listaDisciplina.add(
            Disciplina(
                "3",
                "Matemáticas",
                "",
                false
            )
        )
        this.listaDisciplina.add(
            Disciplina(
                "4",
                "Diseño",
                "",
                false
            )
        )
        this.listaDisciplina.add(
            Disciplina(
                "5",
                "Economía",
                "",
                false
            )
        )
        this.listaDisciplina.add(
            Disciplina(
                "6",
                "Habilidades Sociales",
                "",
                false
            )
        )
        this.listaDisciplina.add(
            Disciplina(
                "7",
                "Física",
                "",
                false
            )
        )
        this.listaDisciplina.add(
            Disciplina(
                "8",
                "Computación",
                "",
                false
            )
        )
        this.listaDisciplina.add(
            Disciplina(
                "9",
                "Quimica",
                "",
                false
            )
        )
        this.listaDisciplina.add(
            Disciplina(
                "10",
                "Música",
                "",
                false
            )
        )
        this.listaDisciplina.add(
            Disciplina(
                "11",
                "Matemáticas Superior",
                "",
                false
            )
        )
        this.listaDisciplina.add(
            Disciplina(
                "12",
                "Ciencias Sociales",
                "",
                false
            )
        )

    }

    private fun initialize() {
        this.txtEducacion = findViewById(R.id.educacion_tutor)
        this.txtOcupacion = findViewById(R.id.ocupacion_tutor)
        this.txtDescripcion = findViewById(R.id.descripcion_tutor)
        this.cbdis1 = findViewById(R.id.categoria_arte)
        this.cbdis2 = findViewById(R.id.categoria_idiomas)
        this.cbdis3 = findViewById(R.id.categoria_matematicas)
        this.cbdis4 = findViewById(R.id.categoria_diseño)
        this.cbdis5 = findViewById(R.id.categoria_economia)
        this.cbdis6 = findViewById(R.id.categoria_habilidadesSociales)
        this.cbdis7 = findViewById(R.id.categoria_fisica)
        this.cbdis8 = findViewById(R.id.categoria_computacion)
        this.cbdis9 = findViewById(R.id.categoria_quimica)
        this.cbdis10 = findViewById(R.id.categoria_musica)
        this.cbdis11 = findViewById(R.id.categoria_matematicasSuperior)
        this.cbdis12 = findViewById(R.id.categoria_cienciasSociales)
        this.btnGuardar = findViewById(R.id.guardar_tutor)
        this.etCuota = findViewById(R.id.cuota_tutoria)

        etCuota.filters = arrayOf<InputFilter>(MoneyFilter(2, 2));

        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = auth.currentUser
        uid = user?.uid!!

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val userRef = ref.child(user?.uid!!)

        //Acá se obtendran los datos del tutor de la base de datos y los ubicara en sus respectivos editText y CheckBox
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                //Obtener informacion de database y ubicarla en sus respectivos campos
                val nivel = p0.child("nivel").value as String?
                val desc = p0.child("Descripcion").value as String?
                val ocupacion = p0.child("ocupacion").value as String?
                val cuota = p0.child("cuota").value as String?

                if (cuota != null) {
                    val fCuota = cuota.toDouble()
                    etCuota.setText("%.2f".format(fCuota))
                }

                txtEducacion.setText(nivel)
                txtOcupacion.setText(ocupacion)
                txtDescripcion.setText(desc)

                if (p0.child("disciplinas").exists()) {
                    for (item in 0..11) {
                        val isSelected = p0.child("disciplinas").child("$item")
                            .child("seleccionado").value as Boolean
                        listaDisciplina[item].seleccionado = isSelected
                        when (item) {
                            0 -> {
                                cbdis1.isChecked = isSelected
                            }
                            1 -> {
                                cbdis2.isChecked = isSelected
                            }
                            2 -> {
                                cbdis3.isChecked = isSelected
                            }
                            3 -> {
                                cbdis4.isChecked = isSelected
                            }
                            4 -> {
                                cbdis5.isChecked = isSelected
                            }
                            5 -> {
                                cbdis6.isChecked = isSelected
                            }
                            6 -> {
                                cbdis7.isChecked = isSelected
                            }
                            7 -> {
                                cbdis8.isChecked = isSelected
                            }
                            8 -> {
                                cbdis9.isChecked = isSelected
                            }
                            9 -> {
                                cbdis10.isChecked = isSelected
                            }
                            10 -> {
                                cbdis11.isChecked = isSelected
                            }
                            11 -> {
                                cbdis12.isChecked = isSelected
                            }
                        }

                    }
                }
            }
        })

    }


    private fun guardar() {

        if (txtDescripcion.text.isNotEmpty() && txtEducacion.text.isNotEmpty() && txtOcupacion.text.isNotEmpty() && etCuota.text.isNotEmpty()) {

            if(txtDescripcion.text.length >= 50){
                val referencia = FirebaseDatabase.getInstance().getReference("Users").child(uid)
                referencia.child("Rol").setValue("Tutor")
                referencia.child("nivel").setValue(txtEducacion.text.toString())
                referencia.child("ocupacion").setValue(txtOcupacion.text.toString())
                referencia.child("Descripcion").setValue(txtDescripcion.text.toString())
                referencia.child("cuota").setValue(etCuota.text.toString())
                referencia.child("disciplinas").setValue(listaDisciplina)
                Toast.makeText(this, "Guardado con exito", Toast.LENGTH_LONG).show()
                finish()
            }else {
                txtDescripcion.setError("La descripcion debe tener al menos 50 caracteres.")
            }

        } else {
            Toast.makeText(this, "Por favor rellenar todos los campos", Toast.LENGTH_LONG).show()
        }
    }
}