package com.example.newsapp

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val url=intent.getStringExtra("Url")
        val detailwebview=findViewById<WebView>(R.id.detailwebview)
        val progressBar= findViewById<ProgressBar>(R.id.progressBar)
        if(url!=null)
        {
            detailwebview.settings.javaScriptEnabled=true
            detailwebview.webViewClient=object: WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility=View.GONE
                    detailwebview.visibility=View.VISIBLE
                }
            }
            detailwebview.loadUrl(url)

        }
    }
}