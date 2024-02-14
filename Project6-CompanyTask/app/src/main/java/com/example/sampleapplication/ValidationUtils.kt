package com.example.sampleapplication

import android.text.TextUtils
import android.util.Patterns

object ValidationUtils {
    fun isValidEmail(text: String): Boolean {
        return !TextUtils.isEmpty(text) && Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }

    fun isTextNotEmpty(text: String?): Boolean {
        return !TextUtils.isEmpty(text)
    }
}
