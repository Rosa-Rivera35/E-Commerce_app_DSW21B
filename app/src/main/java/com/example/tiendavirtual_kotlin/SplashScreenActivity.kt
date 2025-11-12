package com.example.tiendavirtual_kotlin

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer

import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtual_otlin.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        verBienvenida()



    }

    private fun verBienvenida() {
        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {
                startActivity(Intent(applicationContext , MainActivity::class.java))
                finishAffinity()
            }


        }.start()
    }
}