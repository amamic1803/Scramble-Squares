package io.github.amamic1803.scramble_squares

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")

object AppContextHolder {
    lateinit var context: Context
        private set

    fun init(appContext: Context) {
        context = appContext.applicationContext
    }

    fun isInitialized(): Boolean {
        return this::context.isInitialized
    }
}
