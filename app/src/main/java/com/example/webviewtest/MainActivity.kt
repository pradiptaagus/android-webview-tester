package com.example.webviewtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_webview_1.setOnClickListener {
            onClickBtn1()
        }

        btn_webview_2.setOnClickListener {
            onClickBtn2()
        }
    }

    private fun onClickBtn1() {
        val url = et_link.text.toString()

        Log.d("url", url)

        if (url.trim().isNotEmpty()) {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url", url)
            intent.putExtra("type", "regular");
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please insert the url", Toast.LENGTH_LONG).show();
        }
    }

    private fun onClickBtn2() {
        val url = et_link.text.toString()

        Log.d("url", url)

        if (url.trim().isNotEmpty()) {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("type", "custom");
            intent.putExtra("url", url)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please insert the url", Toast.LENGTH_LONG).show();
        }
    }
}