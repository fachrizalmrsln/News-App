package com.id.zul.news.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.EditText
import com.google.gson.Gson
import com.id.zul.news.R
import com.id.zul.news.adapter.DataAdapter
import com.id.zul.news.api.Request
import com.id.zul.news.model.Article
import com.id.zul.news.presenter.MainPresenter
import com.id.zul.news.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: MainPresenter
    private var results: MutableList<Article> = mutableListOf()
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setToolbar()
        setRecycler()
        initializePresenter()
        swipeRefresh()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu?.findItem(R.id.search_item)
        val searchView = searchItem?.actionView as SearchView?

        searchView?.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
            ?.setHintTextColor(Color.WHITE)
        searchView?.queryHint = "search ..."

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val intent = Intent(this@MainActivity, SearchActivity::class.java)
                intent.putExtra("query", query)
                startActivity(intent)
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
        supportActionBar?.title = "iNews"
    }

    private fun initializePresenter() {
        val request = Request()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getNews()
    }

    private fun swipeRefresh() {
        swipe_main.setOnRefreshListener {
            presenter.getNews()
            swipe_main.isRefreshing = false
        }
    }

    private fun setRecycler() {
        recycler_main.layoutManager = LinearLayoutManager(this)
        adapter = DataAdapter(results, this)
        recycler_main.adapter = adapter
    }

    override fun setLoading() {
        progress_main.visibility = View.VISIBLE
        recycler_main.visibility = View.GONE
    }

    override fun setInItData(data: List<Article>) {
        results.clear()
        results.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun unSetLoading() {
        progress_main.visibility = View.GONE
        recycler_main.visibility = View.VISIBLE
    }

}
