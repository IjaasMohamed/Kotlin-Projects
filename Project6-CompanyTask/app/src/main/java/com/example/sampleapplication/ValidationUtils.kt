package com.example.sampleapplication

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

object ValidationUtils {
    fun isValidEmail(text : String) : Boolean {
        return if (TextUtils.isEmpty(text)) return false
        else return Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }
    fun isTextNotEmpty(text: String?) : Boolean {
        return !TextUtils.isEmpty(text)
    }
}