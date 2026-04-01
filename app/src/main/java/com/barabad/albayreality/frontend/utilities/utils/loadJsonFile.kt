package com.barabad.albayreality.frontend.utilities.utils

import android.content.Context

// # reads the enture json file as a string
fun loadJsonFile(context: Context, fileName: String): String {
    return context.assets
        .open(fileName)
        .bufferedReader()
        .use { it.readText() }
}