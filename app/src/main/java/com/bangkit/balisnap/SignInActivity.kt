package com.bangkit.balisnap.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.balisnap.MainActivity
import com.bangkit.balisnap.R
import com.bangkit.balisnap.viewmodel.AuthViewModel

class SignInActivity : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInButton : Button = findViewById(R.id.sign_in_button)
        val emailEditText : EditText = findViewById(R.id.email_edit_text)
        val passwordEditText : EditText = findViewById(R.id.password_edit_text)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            authViewModel.signIn(email, password)
        }

        authViewModel.userLiveData.observe(this) { user ->
            user?.let {
                Toast.makeText(this, "Sign In Successful", Toast.LENGTH_SHORT).show()
                // Navigate to another activity or perform other actions
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        authViewModel.errorLiveData.observe(this) { error ->
            error?.let {
                Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
            }
        }
    }
}