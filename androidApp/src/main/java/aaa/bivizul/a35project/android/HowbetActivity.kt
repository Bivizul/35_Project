@file:Suppress("DEPRECATION")

package aaa.bivizul.a35project.android

import aaa.bivizul.a35project.howbetdata.howbetutil.Howbetcon.HOWBETDOR
import aaa.bivizul.a35project.howbetdata.howbetutil.Howbetcon.HOWBETKOR
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.*
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import im.delight.android.webview.AdvancedWebView

class HowbetActivity : ComponentActivity(), AdvancedWebView.Listener {

    lateinit var howbetwv: AdvancedWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_howbet)

        val howbeturl = intent.getStringExtra(HOWBETKOR) ?: HOWBETDOR

        howbetwv = findViewById<View>(R.id.howbetawv) as AdvancedWebView

        howbetwv.webViewClient = WebViewClient()
        howbetwv.webChromeClient = MyChromeClient()

        howbetwv.setListener(this, this)
        howbetwv.setMixedContentAllowed(false)

        setSettings()

        if (savedInstanceState == null) {
            howbetwv.post {
                kotlin.run { howbetwv.loadUrl(howbeturl) }
            }
        }

        howbetwv.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK &&
                event.action == MotionEvent.ACTION_UP &&
                howbetwv.canGoBack()
            ) {
                howbetwv.goBack()
                return@OnKeyListener true
            }
            false
        })

    }

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        howbetwv.onResume()
    }

    @SuppressLint("NewApi")
    override fun onPause() {
        howbetwv.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        howbetwv.onDestroy()
        super.onDestroy()
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        howbetwv.onActivityResult(requestCode, resultCode, intent)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (!howbetwv.onBackPressed()) {
            return
        }
        finishAndRemoveTask()
        System.exit(0)
    }


    override fun onPageStarted(url: String?, favicon: Bitmap?) {}

    override fun onPageFinished(url: String?) {}

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {}

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?,
    ) {
    }

    override fun onExternalPageRequest(url: String?) {}

    @SuppressLint("SetJavaScriptEnabled")
    private fun setSettings() {
        val howbetws = howbetwv.settings
        howbetws.javaScriptEnabled = true
        howbetws.loadWithOverviewMode = true
        howbetws.allowFileAccess = true
        howbetws.domStorageEnabled = true
        howbetws.builtInZoomControls = true
        howbetws.displayZoomControls = false
        howbetws.useWideViewPort = true
        howbetws.setSupportZoom(true)
        howbetws.setCacheMode(WebSettings.LOAD_NO_CACHE)
        howbetws.userAgentString = howbetws.userAgentString.replace("; wv", "")
    }

    var filePathCallback: ValueCallback<Array<Uri>>? = null
    private val REQUEST_CODE = 100

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        howbetwv.saveState(outState)
    }

    inner class MyChromeClient : WebChromeClient() {

        override fun onShowFileChooser(
            view: WebView,
            filePath: ValueCallback<Array<Uri>>,
            fileChooserParams: FileChooserParams,
        ): Boolean {
            filePathCallback = filePath
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.putExtra(Intent.EXTRA_TITLE, "Image Chooser")
            startActivityForResult(intent, REQUEST_CODE)
            return true
        }

        private var howbetCustomView: View? = null
        private var howbetCustomViewCallback: CustomViewCallback? = null
        private var howbetOriginalOrientation = 0
        private var howbetOriginalSystemUiVisibility = 0

        override fun getDefaultVideoPoster(): Bitmap? {
            return if (howbetCustomView == null) {
                null
            } else BitmapFactory.decodeResource(
                this@HowbetActivity.applicationContext.resources,
                2130837573
            )
        }

        override fun onHideCustomView() {
            (this@HowbetActivity.window.decorView as FrameLayout).removeView(howbetCustomView)
            howbetCustomView = null
            this@HowbetActivity.window.decorView.systemUiVisibility =
                howbetOriginalSystemUiVisibility
            this@HowbetActivity.requestedOrientation = howbetOriginalOrientation
            howbetCustomViewCallback!!.onCustomViewHidden()
            howbetCustomViewCallback = null
        }

        override fun onShowCustomView(
            paramView: View?,
            paramCustomViewCallback: CustomViewCallback?,
        ) {
            if (howbetCustomView != null) {
                onHideCustomView()
                return
            }
            howbetCustomView = paramView
            howbetOriginalSystemUiVisibility =
                this@HowbetActivity.window.decorView.systemUiVisibility
            howbetOriginalOrientation = this@HowbetActivity.requestedOrientation
            howbetCustomViewCallback = paramCustomViewCallback
            (this@HowbetActivity.window.decorView as FrameLayout).addView(
                howbetCustomView,
                FrameLayout.LayoutParams(-1, -1)
            )
            this@HowbetActivity.window.decorView.systemUiVisibility =
                3846 or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

}