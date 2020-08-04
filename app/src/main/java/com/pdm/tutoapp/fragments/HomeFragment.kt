package com.pdm.tutoapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.pdm.tutoapp.R
import com.pdm.tutoapp.TutorFiltradoActivity
import com.pdm.tutoapp.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
private lateinit var seleccionada : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home, container, false)
        bindingView(binding)
        return binding.root
    }

    private fun bindingView (binding : FragmentHomeBinding){
        binding.apply {
            arte.setOnClickListener {
                seleccionada="0"

               /* val intent = Intent(activity,TutorFiltradoActivity::class.java)
                intent.putExtra("seleccion", seleccionada)
                startActivity(intent)*/
            }
            idiomas.setOnClickListener {
                seleccionada="1"
                val intent = Intent(activity,
                    TutorFiltradoActivity::class.java)
                intent.putExtra("seleccion", seleccionada)
                startActivity(intent)
            }
            matematicas.setOnClickListener {
                seleccionada="2"
                val intent = Intent(activity,
                    TutorFiltradoActivity::class.java)
                intent.putExtra("seleccion", seleccionada)
                startActivity(intent)
            }
            diseno.setOnClickListener {
                seleccionada="3"
                val intent = Intent(activity,
                    TutorFiltradoActivity::class.java)
                intent.putExtra("seleccion", seleccionada)
                startActivity(intent)
            }
            economia.setOnClickListener {
                seleccionada="4"
                val intent = Intent(activity,
                    TutorFiltradoActivity::class.java)
                intent.putExtra("seleccion", seleccionada)
                startActivity(intent)
            }
            habilidadesSociales.setOnClickListener {
                seleccionada="5"
                val intent = Intent(activity,
                    TutorFiltradoActivity::class.java)
                intent.putExtra("seleccion", seleccionada)
                startActivity(intent)
            }
            fisica.setOnClickListener {
                seleccionada="6"
                val intent = Intent(activity,
                    TutorFiltradoActivity::class.java)
                intent.putExtra("seleccion", seleccionada)
                startActivity(intent)
            }
            computacion.setOnClickListener {
                seleccionada="7"
                val intent = Intent(activity,
                    TutorFiltradoActivity::class.java)
                intent.putExtra("seleccion", seleccionada)
                startActivity(intent)
            }
            quimica.setOnClickListener {
                seleccionada="8"
                val intent = Intent(activity,
                    TutorFiltradoActivity::class.java)
                intent.putExtra("seleccion", seleccionada)
                startActivity(intent)
            }
            musica.setOnClickListener {
                seleccionada="9"
                val intent = Intent(activity,
                    TutorFiltradoActivity::class.java)
                intent.putExtra("seleccion", seleccionada)
                startActivity(intent)
            }

            mateSuperior.setOnClickListener {
                seleccionada="10"
                val intent = Intent(activity,
                    TutorFiltradoActivity::class.java)
                intent.putExtra("seleccion", seleccionada)
                startActivity(intent)
            }
            cienciasSociales.setOnClickListener {
                seleccionada="11"
                val intent = Intent(activity,
                    TutorFiltradoActivity::class.java)
                intent.putExtra("seleccion", seleccionada)
                startActivity(intent)
            }
        }
    }

}
