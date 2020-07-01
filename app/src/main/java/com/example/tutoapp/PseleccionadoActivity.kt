package com.example.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.appcompat.widget.Toolbar
import com.example.tutoapp.adapter.GvAdapter
import com.example.tutoapp.models.Model
import com.example.tutoapp.ui.SolicitudActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_pseleccionado.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlin.math.round

class PseleccionadoActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var nombre_estudiante: String
    private lateinit var apellido_estudiante: String
    private lateinit var gvDisciplinas: GridView
    private var gv_adapter: GvAdapter? = null

    private lateinit var url: String
    var mStorageRef: StorageReference? = null
    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pseleccionado)
        gvDisciplinas = findViewById(R.id.gv_disciplina)
        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = auth.currentUser

        Log.d("life", "onCreate")

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle("")
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val tutor = intent.getSerializableExtra("tutor") as Model

        nombre_tutor.text = tutor.name
        lastname_tutor.text = tutor.lastname
        location_tutor.text = tutor.location
        ocupation_tutor.text = tutor.ocupacion
        nivel_tutor.text = tutor.nivel
        correo_tutor.text = tutor.correo
        telefono_tutor.text = tutor.telefono
        descripcion_tutor.text = tutor.descripcion


        gridViewSetHeight(gvDisciplinas, tutor.listaDisciplina?.size)

        gv_adapter =
            tutor.listaDisciplina?.let { GvAdapter(this, it) }

        gvDisciplinas?.adapter = gv_adapter



        Picasso.get().load(tutor.ruta).into(image_tutor)
        Picasso.get().load(tutor.ruta).into(user_tutor)

        Picasso.get()
            .load(tutor.ruta)
            .transform(BlurTransformation(this, 25, 3))
            .into(user_tutor)

        mStorageRef = FirebaseStorage.getInstance().reference
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val userRef = ref.child(user?.uid!!)

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                nombre_estudiante = dataSnapshot.child("Name").value as String
                apellido_estudiante = dataSnapshot.child("lastName").value as String
                url = dataSnapshot.child("urlImage").value as String

            }

        })


        action_contactar.setOnClickListener {
            val seleccion = intent?.getStringExtra("seleccion")
            val intent = Intent(this, SolicitudActivity::class.java)
            intent.putExtra("idEstudiante", user?.uid)
            intent.putExtra("nombre_estudiante", nombre_estudiante)
            intent.putExtra("apellido_estudiante", apellido_estudiante)
            intent.putExtra("foto_estudiante", url)
            intent.putExtra("idTutor", tutor.id)
            intent.putExtra("seleccion", seleccion)
            //startActivity(Intent(this,SolicitudActivity::class.java))
            startActivity(intent)
        }

    }

    fun gridViewSetHeight(gridview: GridView, listSize: Int?) {
        var row: Int

        row = if(listSize!!%4 != 0){
            listSize /4 +1
        }else{
            listSize/4
        }

        var params: ViewGroup.LayoutParams = gridview.layoutParams
        val height: Int = 92

        Log.d("as", listSize.toString())
        Log.d("as", row.toString())
        Log.d("as", height.toString())
        params.height = height * row
        Log.d("as", params.height.toString())
        gridview.layoutParams = params

    }
}
