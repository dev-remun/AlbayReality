package com.barabad.albayreality.features

import android.app.Application

class GlobalVar : Application() {

    private var content: String? = null

    fun getContent(): String? {
        return content
    }

    fun setContent(value: String?) {
        content = value
    }
}
