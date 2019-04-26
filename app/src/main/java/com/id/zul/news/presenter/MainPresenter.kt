package com.id.zul.news.presenter

import com.google.gson.Gson
import com.id.zul.news.api.Get
import com.id.zul.news.api.Request
import com.id.zul.news.model.News
import com.id.zul.news.utils.ProviderContext
import com.id.zul.news.view.MainView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(
    private val view: MainView,
    private val apiRepository: Request,
    private val gson: Gson,
    private val context: ProviderContext = ProviderContext()
) {
    fun getNews() {
        view.setLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.getRequestAsync(Get.getNews()).await(),
                News::class.java
            )
            view.setInItData(data.articles)
            view.unSetLoading()
        }
    }
}