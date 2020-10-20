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
import com.epitech.epicture.R
import org.w3c.dom.Text

class HomeActivity : AppCompatActivity() {

    var _accessToken: String? = null
    var _refreshToken: String? = null
    var _accountUsername: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        _accessToken = intent.getStringExtra("access_token")
        _refreshToken = intent.getStringExtra("refresh_token")
        _accountUsername = intent.getStringExtra("account_username")
        println(_accessToken)
        println(_refreshToken)
        println(_accountUsername)
        var _message: String = "Hello " + _accountUsername + " !"
        findViewById<TextView>(R.id.username).setText(_message)
    }
}