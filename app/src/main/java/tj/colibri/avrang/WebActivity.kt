package tj.colibri.avrang

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val link = intent.extras?.getString("redirect")
        Log.e("Link",link.toString())

        webview.webViewClient = WebViewClient()
        webview.webChromeClient = WebChromeClient()

        webview.settings.javaScriptEnabled = true

        if (link != null) {
            webview.loadUrl(link)
        }
    }
}