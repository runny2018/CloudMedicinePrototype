package com.varun.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.logout ->{

                logout()
            }




        }



        return super.onOptionsItemSelected(item)
    }


    fun logout(){

        try {
            mAuth!!.signOut()
            Toast.makeText(this,"Logged Out",Toast.LENGTH_LONG).show()
            finish()

        }catch(ex:Exception){

            Log.d("logout",ex.toString())
            Toast.makeText(this,"Logout Failed",Toast.LENGTH_LONG).show()
        }



    }

}
