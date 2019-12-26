package com.varun.hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

     private var mAuth: FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
    }



    fun createaccount(email:String, password:String){

        mAuth!!.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->

                if(task.isSuccessful){

                    Toast.makeText(applicationContext,"Login Sucessful",Toast.LENGTH_LONG).show()
                    var intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)


                }


                else{

                    Toast.makeText(this,"Login Failed",Toast.LENGTH_LONG).show()

                }

            }




    }


    fun LoginExsisting(email:String,password: String){
        mAuth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){task->

            if(task.isSuccessful){

                Toast.makeText(applicationContext,"Welcome $email",Toast.LENGTH_LONG).show()
                var intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)


            }


            else{

                Toast.makeText(this,"Login Failed",Toast.LENGTH_LONG).show()

            }

            }

        }







    fun create(view:View){

        createaccount(etmail.text.toString(),etpassword.text.toString())


    }

    fun Login(view:View){

        LoginExsisting(etmail.text.toString(),etpassword.text.toString())



    }

}

