package com.pdm.tutoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.pdm.tutoapp.R
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
        var action : NavDirections
        binding.apply {
            arte.setOnClickListener {
                seleccionada="0"
                action =  HomeFragmentDirections.homeFragmentToSearchFragment(seleccionada)
                it.findNavController().navigate(action)
            }
            idiomas.setOnClickListener {
                seleccionada="1"
                action =  HomeFragmentDirections.homeFragmentToSearchFragment(seleccionada)
                it.findNavController().navigate(action)
            }
            matematicas.setOnClickListener {
                seleccionada="2"
                action =  HomeFragmentDirections.homeFragmentToSearchFragment(seleccionada)
                it.findNavController().navigate(action)
            }
            diseno.setOnClickListener {
                seleccionada="3"
                action =  HomeFragmentDirections.homeFragmentToSearchFragment(seleccionada)
                it.findNavController().navigate(action)
            }
            economia.setOnClickListener {
                seleccionada="4"
                action =  HomeFragmentDirections.homeFragmentToSearchFragment(seleccionada)
                it.findNavController().navigate(action)
            }
            habilidadesSociales.setOnClickListener {
                seleccionada="5"
                action =  HomeFragmentDirections.homeFragmentToSearchFragment(seleccionada)
                it.findNavController().navigate(action)
            }
            fisica.setOnClickListener {
                seleccionada="6"
                action =  HomeFragmentDirections.homeFragmentToSearchFragment(seleccionada)
                it.findNavController().navigate(action)
            }
            computacion.setOnClickListener {
                seleccionada="7"
                action =  HomeFragmentDirections.homeFragmentToSearchFragment(seleccionada)
                it.findNavController().navigate(action)
            }
            quimica.setOnClickListener {
                seleccionada="8"
                action =  HomeFragmentDirections.homeFragmentToSearchFragment(seleccionada)
                it.findNavController().navigate(action)
            }
            musica.setOnClickListener {
                seleccionada="9"
                action =  HomeFragmentDirections.homeFragmentToSearchFragment(seleccionada)
                it.findNavController().navigate(action)
            }
            mateSuperior.setOnClickListener {
                seleccionada="10"
                action =  HomeFragmentDirections.homeFragmentToSearchFragment(seleccionada)
                it.findNavController().navigate(action)
            }
            cienciasSociales.setOnClickListener {
                seleccionada="11"
                action =  HomeFragmentDirections.homeFragmentToSearchFragment(seleccionada)
                it.findNavController().navigate(action)
            }
        }
    }

}
