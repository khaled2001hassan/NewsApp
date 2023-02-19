package com.example.newsapp2.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TableLayout
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.newsapp.api.ApiManager
import com.example.newsapp.model.Response
import com.example.newsapp.model.TabsDM
import com.example.newsapp2.R
import com.example.newsapp2.model.ArticleResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import retrofit2.Call
import retrofit2.Callback

class ArticleActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        getTaps()
        initView()
    }

    fun initView() {
        tabLayout = findViewById(R.id.TapsLayout)
        progressBar = findViewById(R.id.ProgressBar)

    }

    fun initListener() {
        tabLayout.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val id = tab?.tag
                getArticle(id.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getTaps() {
        ApiManager.getApi().getTabs(ApiManager.apiKay).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                progressBar.isVisible = false
                Log.e("onResponse: ", "${response.body()}")
                if (response.body()?.code == null) {
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
                    TODO("Not yet implemented")
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