package com.jramirez.pruebazemoga.presentation.application

import android.app.Application

class ZemogaApplication : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: ZemogaApplication
            private set
    }
}