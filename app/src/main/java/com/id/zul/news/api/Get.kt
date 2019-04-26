package com.id.zul.news.api

import android.net.Uri
import com.id.zul.news.BuildConfig

object Get {

    fun getNews(): String {
        return Uri.parse(BuildConfig.BASE_URL)
            .buildUpon()
            .appendPath("v2")
            .appendPath("top-headlines")
            .appendQueryParameter("country", "us")
            .appendQueryParameter("apiKey", BuildConfig.API_KEY)
            .build()
            .toString()
    }

    fun searchNews(query: String): String {
        return Uri.parse(BuildConfig.BASE_URL)
            .buildUpon()
            .appendPath("v2")
            .appendPath("everything")
            .appendQueryParameter("q", query)
            .appendQueryParameter("apiKey", BuildConfig.API_KEY)
            .build()
            .toString()
    }

}