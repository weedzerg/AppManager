package com.manager.managerapp.utils

import android.support.annotation.IntDef
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class NavigatorFragment {
    lateinit var supportFragmentManager: FragmentManager

    constructor(supportFragmentManager: FragmentManager) {
        this.supportFragmentManager = supportFragmentManager
    }

    /**
     * start fragment transaction
     */
    fun fragmentTransaction(id: Int, fragment: Fragment) {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(id, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    /**
     * remove fragment from the back stack
     */
    fun removeFragmentTransaction(fragment: Fragment) {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.remove(fragment);
        transaction.commit();
        supportFragmentManager.popBackStack()
    }

//    /**
//     * start fragment transaction with Aniamtion
//     */
//    fun fragmentTransactionAnimation(id: Int, fragment: Fragment) {
////        var transaction = supportFragmentManager.beginTransaction()
////        transaction.setCustomAnimations(R.animator.slide_up,
////                R.animator.slide_down,
////                R.animator.slide_up,
////                R.animator.slide_down)
////        transaction.replace(id, fragment)
////        transaction.addToBackStack(null)
////        transaction.commit()
//    }

    /**
     * start fragment transaction no addToBackStack
     */
    fun fragmentTransactionNoBackStack(id: Int, fragment: Fragment) {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(id, fragment)
        transaction.commit()
    }

    @IntDef(NONE.toInt(), START.toInt(), FINISH.toInt())
    annotation class AnimationTransaction

    companion object {
        const val NONE: Int = 0
        const val START: Int = 1
        const val FINISH: Int = 2
    }
}