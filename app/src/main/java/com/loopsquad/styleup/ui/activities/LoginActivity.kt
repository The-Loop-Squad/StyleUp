package com.loopsquad.styleup.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopsquad.styleup.MainActivity
import com.loopsquad.styleup.R
import com.loopsquad.styleup.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    fun backButton(view: View){
        finish()
    }

    fun signUp(view: View){
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    fun forgotPassword(view: View){
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

    fun login(view: View){
        val b = ActivityLoginBinding.inflate(layoutInflater)
        var email = b.emailInput.text.toString()
        var password = b.passwordInput.text.toString()
        if(email == "" || password == ""){
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }else{
            // TODO: Implement login feature here...
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}