package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var signinBtn : Button
    lateinit var userloginE :EditText
    lateinit var userpassE : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signinBtn = findViewById(R.id.signinBtn)
        userloginE = findViewById(R.id.userlogin)
        userpassE = findViewById(R.id.userpass)
        val auth = FirebaseAuth.getInstance()

        signinBtn.setOnClickListener {

            var loginText = userloginE.text.toString()
            var loginPassText = userpassE.text.toString()
            if(TextUtils.isEmpty(loginText) || TextUtils.isEmpty(loginPassText)) {
                Toast.makeText(this, "Please provide the valid input", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(loginText, loginPassText)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        // move to the other activity if they are logged in sucessfully
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                            Toast.makeText(this,it.exception?.message, Toast.LENGTH_LONG).show()
                    }
                }

        }
    }
}