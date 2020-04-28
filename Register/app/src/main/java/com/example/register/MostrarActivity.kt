package com.example.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class MostrarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)

        var listview = findViewById<ListView>(R.id.list)

        //otra lista por separado de tutores
        var list = mutableListOf<Model>()

        list.add(Model("Facebook", "Social Network", R.drawable.ic_laptop))
        list.add(Model("WhatsApp", "Social Network", R.drawable.ic_laptop))
        list.add(Model("Instagram", "Social Network", R.drawable.ic_laptop))
        list.add(Model("PornHub", "Social Network", R.drawable.ic_laptop))
        list.add(Model("Xvideos", "Social Network", R.drawable.ic_laptop))

        listview.adapter=MyAdapter(this,R.layout.row,list)


        listview.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            if (position == 0) {
                Toast.makeText(this@MostrarActivity, "Ha seleccionado Facebook", Toast.LENGTH_LONG)
                    .show()
            }
            if (position == 1) {
                Toast.makeText(this@MostrarActivity, "Ha seleccionado WhatsAAPP", Toast.LENGTH_LONG)
                    .show()
            }
            if (position == 2) {
                Toast.makeText(this@MostrarActivity, "Ha seleccionado Instagram", Toast.LENGTH_LONG)
                    .show()
            }
            if (position == 3) {
                Toast.makeText(this@MostrarActivity, "Ha seleccionado PornHub", Toast.LENGTH_LONG)
                    .show()
            }
            if (position == 4) {
                Toast.makeText(this@MostrarActivity, "Ha seleccionado Xvideos", Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

}
