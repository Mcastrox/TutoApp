package com.example.tutoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.tutoapp.databinding.ActivityVerSolicitudBinding
import com.example.tutoapp.models.TutoriaModel
import com.example.tutoapp.viewmodel.TutorViewModel
import com.squareup.picasso.Picasso


class VerSolicitudActivity : AppCompatActivity() {
    private lateinit var idEstudiante: String
    private lateinit var binding : ActivityVerSolicitudBinding
    private lateinit var idTutor : String
    private lateinit var idSolicitud: String
    private var estadoSolicitud : Array<String> = arrayOf("Aceptada","Rechazada")
    private val viewModel by lazy { ViewModelProvider(this).get(TutorViewModel::class.java) }

    var toolbar : Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_solicitud)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ver_solicitud)

        initialize()

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.ver_solicitud_txt)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        
    }

    fun initialize() {
        val solicitud = intent.getSerializableExtra("solicitud") as TutoriaModel

        idEstudiante = solicitud.solicitante
        idTutor = solicitud.tutorSolicitado
        idSolicitud = solicitud.id
        binding.apply {
            nombreEstudiante.text = solicitud.nombre_estudiante + " " + solicitud.apellido_estudiante
            fechaTutoria.text = solicitud.fecha
            ubicacionSolicitud.text = solicitud.direccion
            horaTutoria.text = solicitud.hora
            notasTutoria.text = solicitud.nota
            tvMateria.text = solicitud.categoria
            aceptarTutoria.setOnClickListener {
                viewModel.updateEstadoSolicitud(idTutor,idEstudiante,idSolicitud,estadoSolicitud[0])
                Toast.makeText(this@VerSolicitudActivity, "Has aceptado la solicitud de ${solicitud.nombre_estudiante}", Toast.LENGTH_LONG).show()
                onBackPressed()
            }
            rechazarTutoria.setOnClickListener {
                viewModel.updateEstadoSolicitud(idTutor,idEstudiante,idSolicitud,estadoSolicitud[1])
                Toast.makeText(this@VerSolicitudActivity, "Has rechazado la solicitud de ${solicitud.nombre_estudiante}", Toast.LENGTH_LONG).show()
                onBackPressed()
            }
        }

        Picasso.get().load(solicitud.foto_estudiante).into(binding.fotoEstudiante)

    }

}

