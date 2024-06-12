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

class SignUpActivity : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signUpButton : Button = findViewById(R.id.sign_up_button)
        val nameEditText : EditText = findViewById(R.id.name_edit_text)
        val emailEditText : EditText = findViewById(R.id.email_edit_text)
        val passwordEditText : EditText = findViewById(R.id.password_edit_text)

        signUpButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            authViewModel.signUp(name, email, password)
        }

        authViewModel.userLiveData.observe(this) { user ->
            user?.let {
                Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                // Navigate to another activity or perform other actions
                val intent = Intent(this, SignInActivity::class.java)
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