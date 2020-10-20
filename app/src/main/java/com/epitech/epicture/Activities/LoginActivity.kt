package com.epitech.epicture.Activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.epitech.epicture.R

class LoginActivity : AppCompatActivity() {

    private val clientId: String = "db884af3ed37495"
    private val clientSecret: String = "3a21260660d015e50b27eb46a37a4784d0139659"
    private val host = "api.imgur.com/3"
    val url = "https://api.imgur.com/oauth2/authorize?client_id=$clientId&response_type=token"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val button: Button = findViewById(R.id.btnOpenMain)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
       }
    }
}