package com.example.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.size
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_mostrar.*

class MostrarActivity : AppCompatActivity() {
    private lateinit var uid:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)
        /*var tutor = Model("Facebook", "Red social", R.drawable.ic_chef)
        var tutor1 = Model("WhatsApp", "Red social", R.drawable.ic_laptop)
        var tutor2 = Model("Instagram", "Red social", R.drawable.ic_art)*/
        var me = this // variable para guardar el contexto actual
        var listaTutores = mutableListOf<Model>()
        val ref = FirebaseDatabase.getInstance().getReference("Users") // referencia a la bd
        //qui traigo todos los valores
        ref.addValueEventListener(object  : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(listaTutores.size > 0){
                    listaTutores.clear()
                    list.adapter = null
                }
                for (e in p0.children){
                    var lastName:String = ""
                    var direccion:String = ""
                    var rol: Int = 0
                    if(e.child("lastName").value != null)
                    {
                        lastName = e.child("lastName").value as String
                    }
                    if(e.child("direccion").value != null)
                    {
                        direccion=e.child("direccion").value as String
                    }
                        listaTutores.add(Model(lastName, direccion, R.drawable.ic_laptop))

                }
                var adapter = TutorAdapter(me, listaTutores)
                list.adapter = adapter

                list.setOnItemClickListener { parent, view, position, id ->
                    val intent = Intent(me, PseleccionadoActivity::class.java)
                    intent.putExtra("tutor", listaTutores[position])
                    startActivity(intent)
                }
            }
        })

    }
}


