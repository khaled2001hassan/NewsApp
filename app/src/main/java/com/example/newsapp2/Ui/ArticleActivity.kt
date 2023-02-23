package com.example.newsapp2.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TableLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.api.ApiManager
import com.example.newsapp.model.Response
import com.example.newsapp.model.TabsDM
import com.example.newsapp2.Adapter.ArticleAdapter
import com.example.newsapp2.R
import com.example.newsapp2.model.ArticleResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import retrofit2.Call
import retrofit2.Callback

class ArticleActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var progressBar: ProgressBar
    lateinit var articleRecycleView :RecyclerView
    var adapter :ArticleAdapter= ArticleAdapter(listOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        getTaps()
        initView()
//        initListener()
    }

    fun initView() {
        tabLayout = findViewById(R.id.TapsLayout)
        progressBar = findViewById(R.id.ProgressBar)
        articleRecycleView = findViewById(R.id.ArticleRecycleView)
        articleRecycleView.adapter=adapter

    }

    fun initListener() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val id = tab?.tag as  String
                getArticle(id)
                Log.e( "onTabSelected: ",id )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val id = tab?.tag as  String
                getArticle(id)
            }
        })

    }

    fun getTaps() {
        ApiManager.getApi().getTabs(ApiManager.apiKay).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                progressBar.isVisible = false
                Log.e("onResponse: ", "${response.body()}")
                if (response.code() in 200..300) {
                    showTaps(response.body()?.tabs!!)
                }


            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                progressBar.isVisible = false
                Toast.makeText(this@ArticleActivity, "wrong thing", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun getArticle(tabId: String) {
        ApiManager.getApi().getArtical(ApiManager.apiKay, tabId)
            .enqueue(object : Callback<ArticleResponse> {
                override fun onResponse(
                    call: Call<ArticleResponse>,
                    response: retrofit2.Response<ArticleResponse>
                ) {
                    progressBar.isVisible = false
                adapter.changeData(response.body()?.articles!!)
                }

                override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                    progressBar.isVisible = false
                    Toast.makeText(this@ArticleActivity, "wrong thing", Toast.LENGTH_LONG).show()
                }
            })

    }

    fun showTaps(tabs: List<TabsDM>) {
        tabs.forEach {
            val newTab = tabLayout.newTab()
            newTab.text = it.name
            newTab.tag = newTab.id
            tabLayout.addTab(newTab)
        }

    }
}