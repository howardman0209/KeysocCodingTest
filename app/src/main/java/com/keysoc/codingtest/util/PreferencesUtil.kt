package com.keysoc.codingtest.util

import android.content.Context
import android.os.Build
import com.google.gson.Gson
import com.keysoc.codingtest.model.Bookmark
import java.util.Locale

object PreferencesUtil {

    fun saveLocale(context: Context?, locale: Locale) {
        val localPref = context?.getSharedPreferences(localPrefFileName, Context.MODE_PRIVATE)
        localPref?.edit()?.apply {
            putString(localeLanguagePrefKey, locale.language)
            putString(localeCountryPrefKey, locale.country)
            apply()
        }
    }

    fun getLocale(context: Context?): Locale {
        if (context == null) return Locale.getDefault()
        val localPref = context.getSharedPreferences(localPrefFileName, Context.MODE_PRIVATE)
        val localeLanguage = localPref.getString(localeLanguagePrefKey, "").orEmpty()
        val localeCountry = localPref.getString(localeCountryPrefKey, "").orEmpty()
        return if (localeLanguage.isEmpty() && localeCountry.isEmpty()) {
            val deviceLocale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                context.resources.configuration.locales.get(0)
            } else {
                context.resources.configuration.locale
            }
            Locale(deviceLocale.language, deviceLocale.country)
        } else {
            Locale(localeLanguage, localeCountry)
        }
    }


    fun getLocaleInfo(context: Context?, prefKey: String): String {
        if (context == null) return ""
        val localPref = context.getSharedPreferences(localPrefFileName, Context.MODE_PRIVATE)
        return localPref.getString(prefKey, "").orEmpty()
    }

    fun saveBookmark(context: Context, bookmark: Bookmark) {
        val localPref = context.getSharedPreferences(localPrefFileName, Context.MODE_PRIVATE)
        localPref?.edit()?.putString(bookmarkPrefKey, Gson().toJson(bookmark))?.apply()
    }

    fun getBookmark(context: Context): Bookmark {
        val localPref = context.getSharedPreferences(localPrefFileName, Context.MODE_PRIVATE)
        val bookmarkStr = localPref.getString(bookmarkPrefKey, null)
        val bookmark = try {
            Gson().fromJson(bookmarkStr, Bookmark::class.java)
        } catch (e: java.lang.Exception) {
            null
        }
        return bookmark ?: Bookmark(albumCollectionIdList = emptyList())
    }

}