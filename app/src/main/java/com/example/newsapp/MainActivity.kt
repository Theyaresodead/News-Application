package com.example.newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import library.StackLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()
    val TAG="Utkarsh"
    var pageNum :Int = 1
    var totalresult = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = NewsAdapter(this@MainActivity, articles)
        val newslist = findViewById<RecyclerView>(R.id.newsList)
        newslist.adapter = adapter
        newslist.layoutManager=LinearLayoutManager(this)
        val layoutManager = StackLayoutManager(false,true,1.5f)

        layoutManager.setItemChangedListener(object:StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
                Log.d(TAG,"First Visible Item - ${layoutManager.findFirstVisibleItemPosition()}")
                Log.d(TAG,"Total Count ${layoutManager.itemCount}")
                if(totalresult > layoutManager.itemCount && layoutManager.findFirstVisibleItemPosition() >= layoutManager.itemCount-5)
                {
                    pageNum++
                    getNews()
                }
            }
        })

        newslist.layoutManager = layoutManager
        getNews()
    }

    private fun getNews() {
        val news=NewsService.newsinstance.getHeadLines("in",pageNum)
        news.enqueue(object : Callback<News>{
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(call: Call<News>, response: Response<News>) {
              val news=response.body()
                if(news!=null)
                {
                   Log.d("Utkarsh" ,news.toString())
                    totalresult=news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Utkarsh" ,"Error in fetching", t)
            }
        })
    }
}