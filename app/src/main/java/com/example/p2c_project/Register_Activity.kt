package com.example.p2c_project

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.register.*


class Register_Activity : AppCompatActivity() {
   // private val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        val auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null)
        {

            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

    fun goto_the_next_activity(view: View) {
        val name = registername.text.toString()
        val pass= registerpassword.text.toString()
        val email = registeremail.text.toString()
        val no = registercontactnumber.text.toString()
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(email) ||
            TextUtils.isEmpty(no))
        {
            Toast.makeText(this,"enter valid details",Toast.LENGTH_LONG).show()
        }
        else
        {


            val auth1 = FirebaseAuth.getInstance()
            auth1.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(
                OnCompleteListener {
                    if(it.isSuccessful)
                    {
                       Toast.makeText(this,"user created sucessfull",Toast.LENGTH_LONG).show()
                       val fstore = FirebaseFirestore.getInstance()
                        val fauth = FirebaseAuth.getInstance()
                       val userid= fauth.getCurrentUser()?.getUid().toString()

                        val user = hashMapOf<String,String>()
                        user.put("Name",name)
                        user.put("Phone no.",no)
                        user.put("Password",pass)
                        user.put("E-mail",email)
                        fstore.collection("user").document(userid).set(user)




                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    else
                        Toast.makeText(this,"user not created sucessfull",Toast.LENGTH_LONG).show()
                })



        }
    }



    fun goto_login_page(view: View) {
        startActivity(Intent(this,Login_Activity::class.java))
        finish()
    }
}
