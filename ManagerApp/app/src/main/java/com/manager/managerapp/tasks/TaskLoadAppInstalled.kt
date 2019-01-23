package com.manager.managerapp.tasks

import android.content.Context
import android.os.AsyncTask
import com.manager.managerapp.objects.AppInstallObject
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageStats
import android.graphics.drawable.Drawable
import android.util.Log
import android.os.RemoteException
import com.manager.managerapp.interfaces.ListenerLoadAppInstall
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method


class TaskLoadAppInstalled(private var context: Context,
                           private var listener: ListenerLoadAppInstall)
    : AsyncTask<Void, AppInstallObject, Boolean>() {
    private var size: Long = 0
    override fun doInBackground(vararg params: Void?): Boolean {
        var arrSystem = ArrayList<AppInstallObject>()
        var arrAppInstall = ArrayList<AppInstallObject>()
        getAppInstalled(arrAppInstall, arrSystem)
        return true
    }

    override fun onProgressUpdate(vararg values: AppInstallObject?) {
        super.onProgressUpdate(*values)
        listener.loadingApp(values[0]!!)
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
    }

    fun getAppInstalled(arrAppInstalled: ArrayList<AppInstallObject>,
                        arrSystem: ArrayList<AppInstallObject>) {
        var arrPackageInf = context.packageManager.getInstalledPackages(0)
        for (app in arrPackageInf) {
            var name = ""
            var version: String
            var pgName: String
            var icon: Drawable
            try {
                name = app.applicationInfo.name
            } catch (e: Exception) {
            }
            pgName = app.applicationInfo.packageName
            version = app.versionName
            icon = app.applicationInfo.loadIcon(context.packageManager)
            Log.i("DEBUG", "$name\t$pgName\t$version")
            var appLoad = AppInstallObject(icon, name, pgName, version, 0f, false)
            if (isSystemPackage(app)) {
                arrSystem.add(appLoad)
            } else {
                arrAppInstalled.add(appLoad)
            }
            publishProgress(appLoad)
            getpackageSize(pgName)
        }
    }

    fun getpackageSize(pgName: String) {
        // Create object to access Package Manager
        val pm = context.packageManager
        val getPackageSizeInfo: Method
        try {
            getPackageSizeInfo = pm.javaClass.getMethod(
                    "getPackageSizeInfo", String::class.java,
                    IPackageStatsObserver::class.java)
            getPackageSizeInfo.invoke(pm, pgName,
                    cachePackState()) //Call the inner class
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }

    }


    private fun isSystemPackage(pkgInfo: PackageInfo): Boolean {
        return if (pkgInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0) true else false
    }

    private inner class cachePackState : IPackageStatsObserver.Stub() {
        @Throws(RemoteException::class)
        override fun onGetStatsCompleted(pStats: PackageStats, succeeded: Boolean) {
            Log.w("Package Name", pStats.packageName)
            Log.i("Cache Size", pStats.cacheSize.toString())
            Log.v("Data Size", pStats.dataSize.toString())
            size = pStats.dataSize + pStats.cacheSize
            Log.v("Total Cache Size", " $size")
            Log.v("APK Size", pStats.codeSize.toString())
        }
    }


}
