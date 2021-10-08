package com.example.autentificazone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.autentificazone.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.btnLogin.setOnClickListener {
            when{
                TextUtils.isEmpty(mBinding.etEmail.text.toString().trim { it<=' ' }) ->{
                    Toast.makeText(this@RegisterActivity,
                    "Please enter email.",
                    Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}