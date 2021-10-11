package com.example.autentificazone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.autentificazone.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.tvRegister.setOnClickListener{
            val intent = Intent(this, LoginAcrtivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

        }
        mBinding.btnLogin.setOnClickListener {

            when{
                TextUtils.isEmpty(mBinding.etEmail.text.toString().trim { it<=' ' }) ->{
                    Toast.makeText(this@RegisterActivity,
                    "Please enter email.",
                    Toast.LENGTH_LONG).show()
                }
                TextUtils.isEmpty(mBinding.etPassword.text.toString().trim { it<=' ' }) ->{
                    Toast.makeText(this@RegisterActivity,
                        "Please enter password.",
                        Toast.LENGTH_LONG).show()
                }
                else -> {
                    val email:String = mBinding.etEmail.text.toString().trim { it <= ' ' }
                    val password:String = mBinding.etPassword.text.toString().trim{ it <= ' '}
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {

                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                Toast.makeText(this@RegisterActivity,
                                    "You were registered successfully",
                                    Toast.LENGTH_LONG
                                ).show()

                                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseUser.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            }else{
                                Toast.makeText(this@RegisterActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT).show()
                            }


                        }
                }

            }
        }
    }
}