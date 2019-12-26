package com.varun.hackathon

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null


        var mGoogleSignInClient:GoogleSignInClient ?=null


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

    }








    fun createaccount(email: String, password: String) {

        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {

                    Toast.makeText(applicationContext, "Login Sucessful", Toast.LENGTH_LONG).show()
                    var intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)


                } else {

                    Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()

                }

            }


    }


    val RC_SIGN_IN = 288

    fun hw(view: View){

        Toast.makeText(this,"Button ",Toast.LENGTH_LONG).show()
    }


    fun SigninGoogle (view: View) {

        //Toast.makeText(this,"Button ",Toast.LENGTH_LONG).show()
        val signInIntent: Intent = mGoogleSignInClient!!.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)



    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
               // Toast.makeText(this,"inside try of google",Toast.LENGTH_LONG).show()
                //Toast.makeText(this,"Login success",Toast.LENGTH_LONG).show()
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
              //  Log.w("googletag", "Google sign in failed+ $e")
                Toast.makeText(this,"failed google ",Toast.LENGTH_LONG).show()
                //Toast.makeText(this,"Login failed",Toast.LENGTH_LONG).show()
                // ...
            }
        }
    }


    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("googletag", "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //Toast.makeText(this,"Auth pass firebase",Toast.LENGTH_LONG).show()
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("googletag", "signInWithCredential:success")

                    var intent =Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w("googletag", "signInWithCredential:failure", task.exception)
                   Toast.makeText(this,"Something Went Wrong :(",Toast.LENGTH_LONG).show()

                }

                // ...
            }
    }



    fun LoginExsisting(email: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->

            if (task.isSuccessful) {

                Toast.makeText(applicationContext, "Welcome $email", Toast.LENGTH_LONG).show()
                var intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)


            } else {

                Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()

            }

        }

    }


    fun create(view: View) {

        createaccount(etmail.text.toString(), etpassword.text.toString())


    }

    fun Login(view: View) {

        LoginExsisting(etmail.text.toString(), etpassword.text.toString())


    }

}

