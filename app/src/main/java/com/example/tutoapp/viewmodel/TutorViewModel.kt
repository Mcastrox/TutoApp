package com.example.tutoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tutoapp.TutoriaModel
import com.example.tutoapp.domain.data.network.Repo

class TutorViewModel: ViewModel() {
    val repo = Repo()

    fun postUserData (tutoria: TutoriaModel,idTutor: String): Int
    {

        return repo.postUserData(tutoria,idTutor)
    }

}