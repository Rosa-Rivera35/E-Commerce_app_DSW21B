package com.example.tiendavirtualapp_kotlin.Vendedor

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualapp_kotlin.databinding.ActivityLoginVendedorBinding
import com.google.firebase.auth.FirebaseAuth

class LoginVendedorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginVendedorBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginVendedorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnLoginV.setOnClickListener {
            validarInfo()
        }
        binding.tvRegistrarV.setOnClickListener {
            startActivity(Intent(applicationContext, RegistroVendedorActivity::class.java))
        }

    }
    private var email = ""
    private var password = ""
    private fun validarInfo() {
        email = binding.etEmailV.text.toString().trim()
        password = binding.etPasswordV.text.toString().trim()

        if (email.isEmpty()){
            binding.etEmailV.error = "Ingrese email"
            binding.etEmailV.requestFocus()
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmailV.error = "Email no válido"
            binding.etEmailV.requestFocus()
        }else if (password.isEmpty()){
            binding.etPasswordV.error = "Ingrese password"
            binding.etPasswordV.requestFocus()
        }else{
            loginVendedor()
        }
    }

    private fun loginVendedor() {
        progressDialog.setMessage("Ingresando...")
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this, MainActivityVendedor::class.java))
                finish()

                Toast.makeText(
                    this,
                    "Bienvenido(a)",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(
                    this,
                    "No se pudo iniciar sesión debido a ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

    }
}


