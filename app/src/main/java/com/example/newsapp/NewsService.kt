package com.example.newsapp

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=in&apiKey=3086e27b84f3403ba131a45eef13c707
//https://newsapi.org/v2/everything?q=apple&from=2023-09-04&to=2023-09-04&sortBy=popularity&apiKey=3086e27b84f3403ba131a45eef13c707

const val Base_Url="https://newsapi.org/"
const val API_key="3086e27b84f3403ba131a45eef13c707"
interface NewsInterface{

    @GET("v2/top-headlines?apiKey=$API_key")
    fun getHeadLines(@Query("country") country:String, @Query("page") page:Int) : Call<News>

}
object NewsService{
    val newsinstance:NewsInterface
    init{
        val retrofit = Retrofit.Builder().baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create()).build()
        newsinstance= retrofit.create(NewsInterface::class.java)
    }
}