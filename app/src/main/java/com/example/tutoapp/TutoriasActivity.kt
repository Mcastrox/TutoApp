package com.example.tutoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tutorias.*

class TutoriasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorias)

        var listaTutorias = mutableListOf<TutoriaModel>()
        listaTutorias.add(TutoriaModel("1","holanda","deporte","hola","12:30","hoy","1","2"))
        listaTutorias.add(TutoriaModel("1","holanda","deporte","hola","12:30","hoy","1","2"))


        var adapter = SolicitudAdapter(this, listaTutorias)

        lista_solicitudes.adapter=adapter

    }
}
