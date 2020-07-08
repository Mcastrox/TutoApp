package com.pdm.tutoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.pdm.tutoapp.R
import com.pdm.tutoapp.models.TutoriaModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tutorias.view.*

class SolicitudAdapter(
    private val mContext: Context,
    private val listaTutorias: List<TutoriaModel>
) : ArrayAdapter<TutoriaModel>(mContext, 0, listaTutorias) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.tutorias, parent, false)
        val tutoria = listaTutorias[position]

        layout.nombre_estudiante.text = tutoria.nombre_estudiante
        layout.apellido_estudiante.text = tutoria.apellido_estudiante
        layout.ubicacion.text = tutoria.direccion
        layout.estado.text = tutoria.estado

        if(tutoria.estado.equals("En espera")){
            layout.estado.setTextColor(ContextCompat.getColor(mContext, R.color.yellow))
        }else if (tutoria.estado.equals("Rechazada")){
            layout.estado.setTextColor(ContextCompat.getColor(mContext, R.color.redBrick))
        }else if (tutoria.estado.equals("Aceptada")){
            layout.estado.setTextColor(ContextCompat.getColor(mContext, R.color.green))
        }


        Picasso.get().load(tutoria.foto_estudiante).into(layout.foto_estudiante)
        
        return layout
    }
}