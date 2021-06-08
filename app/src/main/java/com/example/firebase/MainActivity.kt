package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var signupmain : Button
    lateinit var loginmain : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signupmain = findViewById(R.id.signupmain)
        loginmain = findViewById(R.id.loginmain)



        // if the user is logged in already then use the below scripts to prevent it to get logged in again scree.

        var auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        // prevention ends here

        //signup implementation starts
        signupmain.setOnClickListener {
            var intent1 = Intent(this, SignUpActivity::class.java)
            startActivity(intent1)
        }
        // signup implementation ends

        //login implementation starts
        loginmain.setOnClickListener {
            var intent2 = Intent(this, LoginActivity::class.java)
            startActivity(intent2)
        }
        // login implementation ends


    }
}