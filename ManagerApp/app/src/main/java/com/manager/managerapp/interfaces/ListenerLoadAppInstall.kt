package com.manager.managerapp.interfaces

import com.manager.managerapp.objects.AppInstallObject

interface ListenerLoadAppInstall {
    fun loadSucces()
    fun loadingApp(app: AppInstallObject)
    fun loadError()
}