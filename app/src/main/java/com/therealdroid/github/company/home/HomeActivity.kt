package com.therealdroid.github.company.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.therealdroid.github.company.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //just show the user display name after login
        welcomeUser.text = String.format(getString(R.string.welcome_message), intent?.extras!!.getString("username"))
    }

}
