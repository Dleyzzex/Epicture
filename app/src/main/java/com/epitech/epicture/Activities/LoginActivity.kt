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
import android.widget.TextView
import com.epitech.epicture.HomeFragment
import com.epitech.epicture.MainActivity
import com.epitech.epicture.R
import org.w3c.dom.Text
import com.epitech.epicture.Activities.HomeActivity

class LoginActivity : AppCompatActivity() {

    private val clientId: String = "db884af3ed37495"
    private val url = "https://api.imgur.com/oauth2/authorize?client_id=$clientId&response_type=token"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val button: Button = findViewById(R.id.btnOpenMain)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        var url = intent.data
        if (url != null && url.toString().startsWith("epicture://callback"!!)) {
            if (url.getQueryParameter("error") == null) {
                var _uri = Uri.parse(url.toString().replace('#', '?'))
                val _accessToken = _uri.getQueryParameter("access_token")
                val _refreshToken = _uri.getQueryParameter("refresh_token")
                val _accountUsername = _uri.getQueryParameter("account_username")
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                intent.putExtra("access_token", _accessToken)
                intent.putExtra("refresh_token", _refreshToken)
                intent.putExtra("account_username", _accountUsername)
                startActivity(intent)
            }
        }
    }
}