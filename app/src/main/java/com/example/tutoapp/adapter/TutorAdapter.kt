package com.example.tutoapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.tutoapp.models.Model
import com.example.tutoapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*

class TutorAdapter(private val mContext: Context , private val listaTutores : List<Model>): ArrayAdapter<Model>(mContext,0,listaTutores) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.row,parent, false)
        val tutor = listaTutores[position]

        layout.apply {
            textView1.text=tutor.name
            textView2.text=tutor.lastname
            ubicacion_solicitud.text = tutor.location
            textView3.text=tutor.ocupacion
            cuota.text = "%.2f".format(tutor.cuota.toDouble())
        }

        if(tutor.ruta == "0"){
            layout.image.setImageResource(R.drawable.ic_usuario)
        }
        else{
            Picasso.get().load(tutor.ruta).into(layout.image)
        }

        return layout
    }
}