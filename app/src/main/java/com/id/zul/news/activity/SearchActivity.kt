package com.id.zul.news.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.EditText
import com.google.gson.Gson
import com.id.zul.news.R
import com.id.zul.news.adapter.DataAdapter
import com.id.zul.news.api.Request
import com.id.zul.news.model.Article
import com.id.zul.news.presenter.SearchPresenter
import com.id.zul.news.view.SearchView
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchView {

    private lateinit var presenter: SearchPresenter
    private var results: MutableList<Article> = mutableListOf()
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setToolbar()
        initializePresenter()
        setRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu?.findItem(R.id.search_item)
        val searchView = searchItem?.actionView as android.support.v7.widget.SearchView?

        searchView?.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
            ?.setHintTextColor(Color.WHITE)
        searchView?.queryHint = "search ..."

        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.getSearchNews(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun setToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
    }

    private fun initializePresenter() {
        val request = Request()
        val gson = Gson()
        presenter = SearchPresenter(this, request, gson)
        presenter.getSearchNews(intent.getStringExtra("query"))
    }

    private fun setRecycler() {
        recycler_search.layoutManager = LinearLayoutManager(this)
        adapter = DataAdapter(results, this)
        recycler_search.adapter = adapter
    }

    override fun setLoading() {
        progress_search.visibility = View.VISIBLE
        empty_search.visibility = View.GONE
        recycler_search.visibility = View.GONE
    }

    override fun setInItData(data: List<Article>) {
        results.clear()
        results.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun setNotFound() {
        progress_search.visibility = View.GONE
        empty_search.visibility = View.VISIBLE
        recycler_search.visibility = View.GONE
    }

    override fun unSetLoading() {
        progress_search.visibility = View.GONE
        empty_search.visibility = View.GONE
        recycler_search.visibility = View.VISIBLE
    }

}
