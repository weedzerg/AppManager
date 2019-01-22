package com.manager.managerapp.screens.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.manager.managerapp.R
import com.manager.managerapp.screens.fragments.AppInstalledFragment
import com.manager.managerapp.screens.fragments.AppSystemFragment
import com.manager.managerapp.screens.fragments.SettingFragment
import com.manager.managerapp.utils.NavigatorFragment
import kotlinx.android.synthetic.main.activity_manager_app.*

class ManagerAppActivity : AppCompatActivity() {
    private var fragmentInstall: AppInstalledFragment? = null
    private var fragmentSystem: AppSystemFragment? = null
    private var fragmentSetting: SettingFragment? = null

    private lateinit var fragmentNavigator: NavigatorFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_app)
        fragmentNavigator = NavigatorFragment(supportFragmentManager)
        initView()
    }

    fun initView() {
        img_install.setOnClickListener(onClickBottom)
        text_install.setOnClickListener(onClickBottom)
        img_system.setOnClickListener(onClickBottom)
        text_system.setOnClickListener(onClickBottom)
        img_setting.setOnClickListener(onClickBottom)
        text_setting.setOnClickListener(onClickBottom)
        addFragmentInstall()
    }

    fun resetUIBottom() {
        img_install.setImageResource(R.mipmap.ic_launcher)
        img_system.setImageResource(R.mipmap.ic_launcher)
        img_setting.setImageResource(R.mipmap.ic_launcher)
        text_install.setTextColor(baseContext.resources.getColor(R.color.color_grey))
        text_system.setTextColor(baseContext.resources.getColor(R.color.color_grey))
        text_setting.setTextColor(baseContext.resources.getColor(R.color.color_grey))
    }

    fun setupUIBottom() {
        if (fragmentInstall != null && fragmentInstall!!.isVisible) {
            img_install.setImageResource(R.mipmap.ic_launcher_round)
            text_install.setTextColor(baseContext.resources.getColor(R.color.color_black))
            return
        }

        if (fragmentSystem != null && fragmentSystem!!.isVisible) {
            img_system.setImageResource(R.mipmap.ic_launcher_round)
            text_system.setTextColor(baseContext.resources.getColor(R.color.color_black))

            return
        }
        if (fragmentSetting != null && fragmentSetting!!.isVisible) {
            img_setting.setImageResource(R.mipmap.ic_launcher_round)
            text_setting.setTextColor(baseContext.resources.getColor(R.color.color_black))

            return
        }
    }

    /**
     * visible or invisible fragment
     */

    fun addFragmentInstall() {
        if (fragmentInstall == null) {
            fragmentInstall = AppInstalledFragment.newInstance()
        }
        if (fragmentInstall!!.isVisible) return
        fragmentNavigator.fragmentTransactionNoBackStack(fr_fragment.id, fragmentInstall!!)
    }

    fun addFragmentSystem() {
        if (fragmentSystem == null) {
            fragmentSystem = AppSystemFragment.newInstance()
        }
        if (fragmentSystem!!.isVisible) return
        fragmentNavigator.fragmentTransaction(fr_fragment.id, fragmentSystem!!)
    }

    fun addFragmentSetting() {
        if (fragmentSetting == null) {
            fragmentSetting = SettingFragment.newInstance()
        }
        if (fragmentSetting!!.isVisible) return
        fragmentNavigator.fragmentTransaction(fr_fragment.id, fragmentSetting!!)
    }

    fun removeFragment() {
        if (fragmentSystem != null && fragmentSystem!!.isVisible) {
            fragmentNavigator.removeFragmentTransaction(fragmentSystem!!)
        }
        if (fragmentSetting != null && fragmentSetting!!.isVisible) {
            fragmentNavigator.removeFragmentTransaction(fragmentSetting!!)
        }
    }

    /**
     * handle event click item bottom
     */
    private val onClickBottom = object : View.OnClickListener {
        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.img_install, R.id.text_install -> {
                    if (fragmentInstall != null && fragmentInstall!!.isVisible) return
                    resetUIBottom()
                    removeFragment()
                    addFragmentInstall()
                    setupUIBottom()

                }
                R.id.img_setting, R.id.text_setting -> {
                    if (fragmentSystem != null && fragmentSystem!!.isVisible) return
                    resetUIBottom()
                    removeFragment()
                    addFragmentSystem()
                    setupUIBottom()
                }
                R.id.img_system, R.id.text_system -> {
                    if (fragmentSetting != null && fragmentSetting!!.isVisible) return
                    resetUIBottom()
                    removeFragment()
                    addFragmentSetting()
                    setupUIBottom()
                }
            }
        }
    }
}
