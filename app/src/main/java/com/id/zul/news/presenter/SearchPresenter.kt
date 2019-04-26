package com.id.zul.news.presenter

import com.google.gson.Gson
import com.id.zul.news.api.Get
import com.id.zul.news.api.Request
import com.id.zul.news.model.News
import com.id.zul.news.utils.ProviderContext
import com.id.zul.news.view.SearchView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchPresenter(
    private val view: SearchView,
    private val apiRepository: Request,
    private val gson: Gson,
    private val context: ProviderContext = ProviderContext()
) {
    fun getSearchNews(query: String) {
        view.setLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.getRequestAsync(Get.searchNews(query)).await(),
                News::class.java
            )
            if (data.articles.isNullOrEmpty())
                view.setNotFound()
            else {
                view.setInItData(data.articles)
                view.unSetLoading()
            }
        }
    }
}