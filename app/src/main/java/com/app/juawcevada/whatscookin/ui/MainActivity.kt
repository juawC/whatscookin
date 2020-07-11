package com.app.juawcevada.whatscookin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.juawcevada.whatscookin.R
import com.app.juawcevada.whatscookin.di.FragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)
    }

}
