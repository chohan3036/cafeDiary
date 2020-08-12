package com.example.caffo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.caffo.login.SignUpFragment
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception


class LoginActivity : AppCompatActivity() {
    var auth : FirebaseAuth? = null
    var googleSignInClient : GoogleSignInClient? = null
    var GOOGLE_LOGIN_CODE = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        email_login_button.setOnClickListener {
            // 이미 회원가입을 했다면 이메일과 비밀번호 입력 후 로그인
            siginInEamil()
        }
        google_signin_button.setOnClickListener {
            // 구글 소셜 로그인 ; 카카오 추가하기!
            googleLogin()
        }
        email_signup_button.setOnClickListener {
            // 회원가입을 하지 않았다면 회원가입 창(fragment) 띄우기
            var signUpFragment = SignUpFragment()
            var transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_in_down,
                R.anim.slide_out_down, R.anim.slide_out_up)
            transaction.replace(R.id.login_content, signUpFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        // 구글 로그인 옵션 객체를 사용해 로그인 시도
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }


    override fun onStart() {
        super.onStart()
        // 로그인 유지 ; 로그인 유지 체크 박스 만들어서 분기 만들기
        moveMainPage(auth?.currentUser)
    }

    fun googleLogin() {
        var signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, GOOGLE_LOGIN_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_LOGIN_CODE){
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result!!.isSuccess){
                var account = result.signInAccount
                firebaseAuthWithGoogle(account)
            }
        }
    }

    fun firebaseAuthWithGoogle(account : GoogleSignInAccount?){
        var credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener {
                    task ->
                if(task.isSuccessful){
                    //Login
                    moveMainPage(task.result?.user)
                }
                else {
                    //Show the error message
                    print(task.exception?.message)
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    fun siginInEamil(){
        try{
            auth?.signInWithEmailAndPassword(email_edittext.text.toString(), password_edittext.text.toString())
                ?.addOnCompleteListener {
                        task ->
                    if(task.isSuccessful){
                        //Login
                        moveMainPage(task.result?.user)
                    }
                    else {
                        //Show the error message
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                    }
                }
            } catch(e: Exception) { }
    }

    fun moveMainPage(user:FirebaseUser?){
        if(user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}
