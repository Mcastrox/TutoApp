package com.example.tutoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutoapp.domain.data.network.Repo
import com.example.tutoapp.models.TutoriaModel

class EstudianteViewModel : ViewModel() {
    val repo = Repo()

    fun getEstudianteSolicitud(idEstudiante: String): LiveData<MutableList<TutoriaModel>> {
        val mutableData = MutableLiveData<MutableList<TutoriaModel>>()
        repo.getStudentUserSolicitud(idEstudiante).observeForever { solicitudes->
            mutableData.value = solicitudes
        }

        return mutableData
    }
}