package com.id.zul.news.view

import com.id.zul.news.model.Article

interface MainView {
    fun setLoading()
    fun setInItData(data: List<Article>)
    fun unSetLoading()
}