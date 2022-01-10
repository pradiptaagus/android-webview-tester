package com.example.webviewtest

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.webviewtest.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    private lateinit var myWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        Log.d("URL", Uri.parse(intent.getStringExtra("url")).toString())
        Log.d("Type", intent.getStringExtra("type").toString())

        myWebView = webview
        myWebView.settings.javaScriptEnabled = true
        if (intent.getStringExtra("type") == "custom") {
            myWebView.webViewClient = CustomWebViewClient()
        } else {
            myWebView.webViewClient = WebViewClient()
        }
        myWebView.loadUrl(intent.getStringExtra("url").toString())

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }

        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
            myWebView.goBack()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

    private class CustomWebViewClient: WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            url: String
        ): Boolean {
            if (url.startsWith("tel:")) {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
                view?.context?.startActivity(intent)
            } else {
                view?.loadUrl(url)
            }

            return true
        }
    }
}