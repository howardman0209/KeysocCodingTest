package com.keysoc.codingtest

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate
import com.keysoc.codingtest.util.LIFECYCLE
import com.keysoc.codingtest.util.PreferencesUtil.getLocale


class MainApplication : Application(), ActivityLifecycleCallbacks {

    companion object {
        var activitiesAlive = ArrayList<String>()
    }

    private var currentActiveActivity: Activity? = null
    private val localizationDelegate = LocalizationApplicationDelegate()

    override fun onCreate() {
        super.onCreate()
        Log.d(LIFECYCLE, "app onCreate")
        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Log.d(LIFECYCLE, "${activity.javaClass.name} onCreated")
        if (!activitiesAlive.contains(activity.javaClass.name)) {
            activitiesAlive.add(activity.javaClass.name)
            Log.d(LIFECYCLE, activitiesAlive.toString())
        }
    }

    override fun onActivityStarted(activity: Activity) {
//        Log.d(LIFECYCLE, "${activity.javaClass.name} onStarted")
    }

    override fun onActivityResumed(activity: Activity) {
//        Log.d(LIFECYCLE, "${activity.javaClass.name} onResumed")
        currentActiveActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {
//        Log.d(LIFECYCLE, "${activity.javaClass.name} onPaused")
        currentActiveActivity = null
    }

    override fun onActivityStopped(activity: Activity) {
        Log.d(LIFECYCLE, "${activity.javaClass.name} onStopped")
    }

    override fun onActivitySaveInstanceState(activity: Activity, savedInstanceState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        Log.d(LIFECYCLE, "${activity.javaClass.name} onDestroyed")
        activitiesAlive.remove(activity.javaClass.name)
    }

    override fun attachBaseContext(base: Context) {
        localizationDelegate.setDefaultLanguage(base, getLocale(base))
        super.attachBaseContext(localizationDelegate.attachBaseContext(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localizationDelegate.onConfigurationChanged(this)
    }

    override fun getApplicationContext(): Context {
        return localizationDelegate.getApplicationContext(super.getApplicationContext())
    }
}