package com.ilya.usersupgrade

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ilya.usersupgrade.databinding.ActivityMainBinding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ViewModelComponent

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        activity = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    
    companion object {
        private lateinit var activity: MainActivity
    }
    
    @Module
    @InstallIn(ViewModelComponent::class)
    object MainActivityProvider {
        @Provides
        fun provideMainActivity(): MainActivity {
            return activity
        }
    }
    
}