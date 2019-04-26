package com.id.zul.news.view

import com.id.zul.news.model.Article

interface SearchView {
    fun setLoading()
    fun setInItData(data: List<Article>)
    fun setNotFound()
    fun unSetLoading()
}