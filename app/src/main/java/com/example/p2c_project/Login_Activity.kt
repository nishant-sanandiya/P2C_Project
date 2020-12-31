package com.example.p2c_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login.*

class Login_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }

    fun logintoapp(view: View) {
        val name = loginemailornumber.text.toString()
        val pass= loginpassword.text.toString()

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pass))
        {
            Toast.makeText(this,"enter valid details", Toast.LENGTH_LONG).show()

        }
        else
        {


            val auth1 = FirebaseAuth.getInstance()

            auth1.signInWithEmailAndPassword(name,pass).addOnCompleteListener(
                OnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(this,"login sucessfull", Toast.LENGTH_LONG).show()

                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    else
                        Toast.makeText(this,"you havn't create account", Toast.LENGTH_LONG).show()
                })



        }

    }
    fun gotoregisterpage(view: View) {
        startActivity(Intent(this,Register_Activity::class.java))
        finish()
    }

    fun forgotpassword(view: View) {
        Toast.makeText(this,"remaining to make this link handler",Toast.LENGTH_LONG).show()
    }


}