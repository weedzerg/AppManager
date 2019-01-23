package com.manager.managerapp.objects

import android.graphics.drawable.Drawable

class AppInstallObject(
        var icon: Drawable,
        var name: String,
        var packagename: String,
        var version: String,
        var size: Float,
        var isNewVersion: Boolean) {

    override fun toString(): String {
        return "$name\t$packagename\t$version"
    }
}