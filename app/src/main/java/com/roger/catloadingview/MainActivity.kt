package com.roger.catloadingview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.roger.catloadinglibrary.CatLoadingView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.button).setOnClickListener {
            CatLoadingView().show(
                supportFragmentManager,
                ""
            )
        }
    }
}