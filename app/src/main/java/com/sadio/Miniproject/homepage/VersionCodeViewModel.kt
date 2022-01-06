package com.sadio.Miniproject.homepage

import androidx.lifecycle.ViewModel
import com.sadio.Miniproject.BuildConfig

class VersionCodeViewModel : ViewModel() {

    var versionCode = BuildConfig.VERSION_CODE
    fun getVersionCode(): String {
        return "Numéro de versionn" + versionCode.toString()
    }
}