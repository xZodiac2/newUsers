package com.ilya.usersupgrade

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.ilya.presentation.GreetingFragment
import com.ilya.presentation.GreetingFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        activity = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    
    companion object {
        lateinit var activity: MainActivity
    }
    
}