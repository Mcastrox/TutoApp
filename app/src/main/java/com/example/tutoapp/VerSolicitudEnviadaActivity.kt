package com.example.tutoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.tutoapp.databinding.ActivityVerSolicitudEnviadaBinding
import com.example.tutoapp.models.TutoriaModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pseleccionado.*

class VerSolicitudEnviadaActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVerSolicitudEnviadaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_solicitud_enviada)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ver_solicitud_enviada)
        initialize()
    }

    fun initialize() {

        val solicitud = intent.getSerializableExtra("solicitud") as TutoriaModel

        binding.apply {
            nombreTutor.text = solicitud.nombre_tutor + " " + solicitud.apellido_tutor
            ubicacionSolicitud.text = solicitud.direccion
            tvMateria.text = solicitud.categoria
            fechaTutoria.text = solicitud.fecha
            horaTutoria.text = solicitud.hora
            notasTutoria.text = solicitud.nota
        }
        Picasso.get().load(solicitud.foto_tutor).into(binding.fotoTutor)

    }
}