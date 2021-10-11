package com.example.autentificazone

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration

import com.example.autentificazone.databinding.ActivityLoginAcrtivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginAcrtivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mBinding: ActivityLoginAcrtivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityLoginAcrtivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        mBinding.btnLogin.setOnClickListener {

            when {
                TextUtils.isEmpty(mBinding.etEmail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@LoginAcrtivity,
                        "Please enter email.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.isEmpty(mBinding.etPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@LoginAcrtivity,
                        "Please enter password.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    val email: String = mBinding.etEmail.text.toString().trim { it <= ' ' }
                    val password: String = mBinding.etPassword.text.toString().trim { it <= ' ' }
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {


                                Toast.makeText(
                                    this@LoginAcrtivity,
                                    "You logged in successfully",
                                    Toast.LENGTH_LONG
                                ).show()

                                val intent = Intent(this@LoginAcrtivity, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@LoginAcrtivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }
                }
            }
        }
    }
}