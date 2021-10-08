package com.example.autentificazone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.autentificazone.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")

        mBinding.tvUserId.text = "User ID: $userId"
        mBinding.tvEmailId.text ="Email ID: $emailId"
        mBinding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity, LoginAcrtivity::class.java))
            finish()
        }
    }

}