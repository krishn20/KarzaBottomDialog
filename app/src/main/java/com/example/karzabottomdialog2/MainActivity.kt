package com.example.karzabottomdialog2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.karzabottomdialog2.HomeFragment
import com.example.karzabottomdialog2.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.parent_frame_layout, HomeFragment()).commit()
    }

}