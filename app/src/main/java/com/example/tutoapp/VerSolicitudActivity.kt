package com.example.tutoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tutoapp.databinding.ActivityVerSolicitudBinding
import com.example.tutoapp.models.TutoriaModel
import com.example.tutoapp.viewmodel.SolicitudViewModel
import com.squareup.picasso.Picasso


class VerSolicitudActivity : AppCompatActivity() {
    private lateinit var btnAceptar: Button
    private lateinit var btnRechazar: Button
    private lateinit var imgEstudiante: ImageView
    private lateinit var idEstudiante: String
    private lateinit var binding : ActivityVerSolicitudBinding
    private val viewModel by lazy { ViewModelProvider(this).get(SolicitudViewModel::class.java) }

    var toolbar : Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_solicitud)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ver_solicitud)

        initialize()
        observerData()

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.ver_solicitud_txt)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        
    }

    fun initialize() {
        imgEstudiante = findViewById(R.id.foto_estudiante)
        val solicitud = intent.getSerializableExtra("solicitud") as TutoriaModel

        binding.apply {
            nombreEstudiante.text = solicitud.nombre_estudiante + " " + solicitud.apellido_estudiante
            fechaTutoria.text = solicitud.fecha
            ubicacionSolicitud.text = solicitud.direccion
            horaTutoria.text = solicitud.hora
            notasTutoria.text = solicitud.nota
            tvMateria.text = solicitud.categoria
        }
        idEstudiante = solicitud.solicitante

    }

    private fun observerData() {
        viewModel.getUserSolicitud(idEstudiante).observe(this, Observer {
            //it trae el objeto persona de la base de datos
            Picasso.get().load(it.urlImage).into(imgEstudiante)
        })

    }
}

