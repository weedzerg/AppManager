// IPackageStatsObserver.aidl
package com.manager.managerapp;

// Declare any non-default types here with import statements

 interface IPackageStatsObserver {
    void onGetStatsCompleted(in PackageStats pStats, boolean succeeded);
}
