package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.sign

class SignUpActivity : AppCompatActivity() {

    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var confirmpass : EditText
    lateinit var signup : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        confirmpass = findViewById(R.id.confirmPass)
        signup = findViewById(R.id.signUp)

        val auth = FirebaseAuth.getInstance()
        signup.setOnClickListener {

            // get input
            val emailText = email.text.toString()
            val passwordText = password.text.toString()
            val confirmpassText = confirmpass.text.toString()
            // input ends here

            // validation of the user input
            if(TextUtils.isEmpty(emailText) || TextUtils.isEmpty(passwordText) || TextUtils.isEmpty(confirmpassText)){
                Toast.makeText(this, "Please provide the valid input", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(passwordText.length < 6){
                Toast.makeText(this, "Password should have atleast 6 characters", Toast.LENGTH_LONG).show()
                return@setOnClickListener

            }
            if(passwordText != confirmpassText){
                Toast.makeText(this, "Password does not match", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            // validation complete here

            // authenticatio progress
            auth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener {task ->
                      if(task.isSuccessful){
                          Toast.makeText(this,"Register Sucessfull", Toast.LENGTH_LONG).show()
                      } else {
                          Toast.makeText(this,"Something went wrong" + task.exception?.message, Toast.LENGTH_LONG).show()
                      }
                }

            // authenticatio ends here

            /* for the forget password user has to made the forget button/text and make that as onClickListner and use the below format for
            geting the formget password
            */

            // auth.sendPasswordResetEmail(emailText)
        }

    }
}

