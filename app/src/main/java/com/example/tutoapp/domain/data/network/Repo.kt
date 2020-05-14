package com.example.tutoapp.domain.data.network

import com.example.tutoapp.TutoriaModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class Repo {
    fun postUserData (tutoria: TutoriaModel, idTutor:String) :  Int {
        var db = FirebaseFirestore.getInstance().collection("Usuarios")
        db.document(idTutor).collection("Solicitudes").document(tutoria.id).set(tutoria)
        return 1
    }
}
