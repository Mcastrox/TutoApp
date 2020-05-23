package com.example.tutoapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import com.example.tutoapp.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
private lateinit var seleccionada : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)

        binding.matematicas.setOnClickListener {
            seleccionada="2"
            val intent = Intent(activity,TutorFiltradoActivity::class.java)
            intent.putExtra("seleccion", seleccionada)
            startActivity(intent)
        }

        return binding.root

    }

}
