package com.example.caffo.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.caffo.MainActivity
import com.example.caffo.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_signup.*
import java.lang.Exception

class SignUpFragment : Fragment() {
    var fragmentView: View? = null
    var auth : FirebaseAuth? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = LayoutInflater.from(activity).inflate(R.layout.fragment_signup, container, false)
        auth = FirebaseAuth.getInstance()
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this_email_signup_button.setOnClickListener {
            signinAndSignup()
        }
    }

    fun signinAndSignup(){
        try {
            auth?.createUserWithEmailAndPassword(email_edittext.text.toString(), password_edittext.text.toString())
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //Creating a user account
                        Toast.makeText(context, "You've signed up successfully", Toast.LENGTH_LONG).show()
                        moveMainPage(task.result?.user)
                    } //else if ( ){

                    //}
                    else if (!task.exception?.message.isNullOrEmpty()) {
                        //Show the error message
                        println(task.exception)
                        Toast.makeText(context, task.exception?.message, Toast.LENGTH_LONG).show()
                    } else {
                        //Unknown status
                        Toast.makeText(context, task.exception?.message, Toast.LENGTH_LONG).show()
                    }
                }
        } catch(e: Exception) { }
    }

    fun moveMainPage(user: FirebaseUser?){
        if(user != null) {
            startActivity(Intent(context, MainActivity::class.java))
        }
    }
}