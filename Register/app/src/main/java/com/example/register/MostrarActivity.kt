package com.example.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MostrarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var tutores :ArrayList<Model> = ArrayList()
        tutores.add(Model("John","Pija corta",R.drawable.ic_chef))
        tutores.add(Model("Wick","Pija corta",R.drawable.ic_chef))
        tutores.add(Model("Roland","Pija corta",R.drawable.ic_chef))

        val lista = findViewById<ListView>(R.id.lista)

        val adaptador = AdapterTutores(this,tutores)

        lista.adapter=adaptador
        lista.onItemClickListener= AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(this,tutores.get(position).tittle,Toast.LENGTH_SHORT).show()
        }
    }
}
