package com.epitech.epicture.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.epitech.epicture.*
import kotlinx.android.synthetic.main.activity_home.*

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
        var _message: String = "Hello " + _accountUsername + " !"
        //findViewById<TextView>(R.id.username).setText(_message)


        var accountFragment = AccountFragment(_accessToken!!, _refreshToken!!, _accountUsername!!)
        var favFragment = FavFragment(_accessToken!!, _refreshToken!!, _accountUsername!!)
        var searchFragment = SearchFragment()
        var uploadFragment = UploadFragment(_accessToken!!)

        makeCurrentFragment(accountFragment)

        bottom_bar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_account -> makeCurrentFragment(accountFragment)
                R.id.ic_fav -> makeCurrentFragment(favFragment)
                R.id.ic_search -> makeCurrentFragment(searchFragment)
                R.id.ic_upload -> makeCurrentFragment(uploadFragment)
            }
            true
        }
    }

    fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    override fun onResume() {
        super.onResume()
    }
}