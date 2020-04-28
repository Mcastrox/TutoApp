package com.example.register

import android.media.Image

class Model( tittle:String,description : String, img : Int) {
    var tittle:String=""
    var description:String=""
    var img : Int = 0
    init {
        this.tittle=tittle
        this.description=description
        this.img=img
    }
}