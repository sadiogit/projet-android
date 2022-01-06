package com.sadio.Miniproject.homepage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.sadio.Miniproject.apis.view.ApisActivity
import com.sadio.Miniproject.databinding.ActivityHomepageBinding


class HomepageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: VersionCodeViewModel = ViewModelProvider(this).get(VersionCodeViewModel::class.java)
        binding.textViewVersionCode.text = viewModel.getVersionCode()
    }


    fun goToApis(view: View) {
        val intent = Intent(this, ApisActivity::class.java)
        startActivity(intent)
    }

    fun goToConnection(view: View) {
        // TODO conection
    }

}
