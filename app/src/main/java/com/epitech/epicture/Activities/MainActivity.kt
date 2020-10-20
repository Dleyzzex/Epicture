package com.epitech.epicture

import android.os.Bundle
import android.os.Handler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.coroutines.withTimeout
import android.content.Intent
import com.epitech.epicture.Activities.LoginActivity

class MainActivity : AppCompatActivity() {

    private var timeout = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}