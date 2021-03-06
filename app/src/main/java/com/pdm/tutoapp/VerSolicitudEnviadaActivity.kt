package com.pdm.tutoapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.pdm.tutoapp.databinding.ActivityVerSolicitudEnviadaBinding
import com.pdm.tutoapp.models.RatingModel
import com.pdm.tutoapp.models.TutoriaModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pseleccionado.*

class VerSolicitudEnviadaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerSolicitudEnviadaBinding
    private var value: Float = 0.0f
    private var avg : Float = 0.0f
    var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_solicitud_enviada)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ver_solicitud_enviada)

        toolbar = binding.toolbar
        toolbar?.setTitle(R.string.ver_solicitud_enviada_txt)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        initialize()
    }

    @SuppressLint("SetTextI18n")
    fun initialize() {

        val solicitud = intent.getSerializableExtra("solicitud") as TutoriaModel

        binding.apply {
            nombreTutor.text = solicitud.nombre_tutor + " " + solicitud.apellido_tutor
            ubicacionSolicitud.text = solicitud.direccion
            tvMateria.text = solicitud.categoria
            fechaTutoria.text = solicitud.fecha
            horaTutoria.text = solicitud.hora
            notasTutoria.text = solicitud.nota
            status.text = solicitud.estado
            monto.text = solicitud.cuota_total
            duracion.text = solicitud.duracion + " h"

            if (status.text == "En espera") {
                status.setTextColor(getColor(R.color.yellow))
            } else if (status.text == "Rechazada") {
                status.setTextColor(getColor(R.color.redBrick))
            } else if (status.text == "Aceptada") {
                status.setTextColor(getColor(R.color.green))
            }

            var modelDialog = AlertDialog.Builder(this@VerSolicitudEnviadaActivity)
            val dialogView = layoutInflater.inflate(R.layout.rating_alert, null)
            val btnCancel = dialogView.findViewById<TextView>(R.id.action_cancelar)
            val btnRate = dialogView.findViewById<TextView>(R.id.action_calificar)
            val rating = dialogView.findViewById<RatingBar>(R.id.rating)

            modelDialog.setView(dialogView)

            rating.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener {
                override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, b: Boolean) {
                    value = rating
                }
            })

            val tutorRef = FirebaseDatabase.getInstance().getReference("Users")
                .child(solicitud.tutorSolicitado)

            var alert_dialog = modelDialog.create()

            tutorRef.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {

                    var ratings = arrayListOf<RatingModel>()
                    if (p0.child("ratings").exists()) {
                        val count: Long = p0.child("ratings").childrenCount - 1
                        for (item in 0..count) {
                            val value =
                                p0.child("ratings").child("$item").child("value").value as String
                            ratings.add(RatingModel(value))
                        }

                        AverageRating( ratings )

                    }

                    myRate.setOnClickListener {
                        alert_dialog.show()

                        btnCancel.setOnClickListener {
                            alert_dialog.dismiss()
                        }

                        btnRate.setOnClickListener {
                            ratings?.add(RatingModel(value.toString()))
                            tutorRef.child("ratings").setValue(ratings)
                            alert_dialog.dismiss()
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }

                    }

                }
            })

        }

        Picasso.get().load(solicitud.foto_tutor).into(binding.fotoTutor)

    }

    private fun AverageRating(ratings: ArrayList<RatingModel>?){

        if (ratings != null) {
            for( rating in ratings){
                avg += rating.value!!.toFloat() / ratings.size
            }
        }

        rating1.rating = avg

    }

}


