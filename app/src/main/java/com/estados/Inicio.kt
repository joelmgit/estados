package com.estados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.estados.databinding.ActivityInicioBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Inicio : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityInicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.signUpButton.setOnClickListener {registerUser()}
        binding.signInButton.setOnClickListener {loginUser()}
    }

    private fun getFields(): Pair<String, String>{
        return Pair(binding.emailField.text.toString(),
            binding.passField.text.toString())
    }

    private fun registerUser() {
        val(email, pass) = getFields()
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this){task->
            if(task.isSuccessful){
                val user = auth.currentUser
                updateLoggedUser(user)
            }else{
                Toast.makeText(baseContext, "Fallo al registrar usuario", Toast.LENGTH_LONG).show()
                updateLoggedUser(null)
            }
        }
    }

    private fun loginUser() {
        val(email, pass) = getFields()
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this){task->
            if(task.isSuccessful){
                val user = auth.currentUser
                updateLoggedUser(user)
            }else{
                Toast.makeText(baseContext, "Fallo al ingresar usuario", Toast.LENGTH_LONG).show()
                updateLoggedUser(null)
            }
        }
    }

    private fun updateLoggedUser(user: FirebaseUser?) {
        if(user != null){
            val intent = Intent(this, Central::class.java)
            startActivity(intent)
        }
    }

    public override fun onStart() {
        super.onStart()
        updateLoggedUser(auth.currentUser)
    }
}