package com.manager.managerapp.screens.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.manager.managerapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
        btn_scan.setOnClickListener {
            var intent = Intent(this, ManagerAppActivity::class.java)
            startActivity(intent)
        }
    }
}
