package com.manager.managerapp.screens.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.manager.managerapp.R
import com.manager.managerapp.interfaces.ListenerLoadAppInstall
import com.manager.managerapp.objects.AppInstallObject
import com.manager.managerapp.tasks.TaskLoadAppInstalled
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        pulsator.start()
        TaskLoadAppInstalled(this,listener).execute()
    }

    fun initView() {
        btn_scan.setOnClickListener {
            var intent = Intent(this, ManagerAppActivity::class.java)
            startActivity(intent)
        }
    }

    private val listener=object :ListenerLoadAppInstall{
        override fun loadSucces() {

        }

        override fun loadingApp(app:AppInstallObject) {
            img_app.setImageDrawable(app.icon)
        }

        override fun loadError() {
        }

    }
}
