package com.example.filmku.util

import java.io.IOException

class NoInternetException : IOException() {
    override val message: String
        get() =
            "No internet available, please check your connected WiFi or Data"
}
