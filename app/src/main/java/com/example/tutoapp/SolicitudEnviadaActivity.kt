package com.example.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tutoapp.adapter.SolicitudEnviadaAdapter
import com.example.tutoapp.databinding.ActivitySolicitudEnviadaBinding
import com.example.tutoapp.viewmodel.TutorViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_solicitud_enviada.*

class SolicitudEnviadaActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var uidEstudiante: String
    private lateinit var adapter: SolicitudEnviadaAdapter
    private lateinit var binding: ActivitySolicitudEnviadaBinding

    private val viewModel by lazy { ViewModelProvider(this).get(TutorViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitud_enviada)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_solicitud_enviada)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        getCurrentUser()
        observerData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.solicitud_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            R.id.next_page -> {
                startActivity(Intent(this, AtributesActivity::class.java))
                return true
            }

            android.R.id.home -> {
                onBackPressed()
                true
            }

            else -> (return super.onOptionsItemSelected(item))
        }
    }


    private fun getCurrentUser() {
        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = auth.currentUser
        uidEstudiante = user?.uid!!
    }

    private fun observerData() {
        viewModel.getEstudianteSolicitud(uidEstudiante).observe(this, Observer {
            adapter = SolicitudEnviadaAdapter(this, it)
            lista_enviadas.adapter = adapter

            if (it.isNullOrEmpty()) {
                binding.empty.visibility = View.VISIBLE
                binding.listaEnviadas.visibility = View.GONE

            } else {
                binding.empty.visibility = View.GONE
                binding.listaEnviadas.visibility = View.VISIBLE

                lista_enviadas.setOnItemClickListener { parent, view, position, id ->
                    val intent = Intent(this, VerSolicitudEnviadaActivity::class.java)
                    intent.putExtra("solicitud", it[position])
                    startActivity(intent)
                }

            }

        })
    }
}