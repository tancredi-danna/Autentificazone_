package com.example.autentificazone

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.autentificazone.databinding.ActivityLoginAcrtivityBinding
import com.google.firebase.auth.FirebaseAuth

class LoginAcrtivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginAcrtivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginAcrtivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}