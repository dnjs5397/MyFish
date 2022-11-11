package com.swkim.myfish

import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.swkim.myfish.databinding.ActivityWorkingBinding
import java.lang.Exception
import java.util.jar.Manifest
import android.content.Intent

class WorkingActivity : AppCompatActivity() {

    private val mFilePathCallback: ValueCallback<*>? = null
    private lateinit var binding: ActivityWorkingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWorkingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val b: Boolean = checkPermission()
            if (b == false) {
                requestPermission()
            } else {
                setUpUI()
            }

        } else {
            setUpUI()
        }

        setContentView(binding.root)

        val spinner = binding.spinner
        spinner.adapter =
            ArrayAdapter.createFromResource(this, R.array.itemList, R.layout.spinnerlayout)

        spinner.onItemClickListener = object:AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                binding.seeButton.setOnClickListener {
                    startActivity(Intent(this@WorkingActivity, gazami_detail::class.java))
                    finish()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TODO("Not yet implemented")
            }

        }

    }

    private fun setUpUI() {
        binding.apply {
            webView.apply {
                setBackgroundColor(Color.TRANSPARENT)
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                    }

                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        // 주소파싱 후,
                        // 이동없음 -> true, 이동하기 -> false
                        return false
                    }
                }

                settings.apply {
                    javaScriptEnabled = true
                    builtInZoomControls = false
                    displayZoomControls = false
                    cacheMode = WebSettings.LOAD_DEFAULT
                    allowFileAccess = false
                    allowUniversalAccessFromFileURLs = false
                    allowFileAccessFromFileURLs = false
                    domStorageEnabled = true
                }

                addJavascriptInterface(AndroidJavascriptInterface(), "android")
                setWebChromeClient(MyChrome())

                val sUrl = "http://118.67.143.76:4000/api"
                loadUrl(sUrl)
            }
        }
    }

    final var FILE_CHOOSE: Int = 111
    fun takePicture() {
        Intent(Intent.ACTION_GET_CONTENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            setType("image/*")
            startActivityForResult(Intent.createChooser(this, ""), FILE_CHOOSE)
        }
    }

    var mFileChooserCallback : ValueCallback<Array<Uri>>? =null
    inner class MyChrome : WebChromeClient(){
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        override fun onShowFileChooser(
            webView: WebView?,
            filePathCallback: ValueCallback<Array<Uri>>?,
            fileChooserParams: WebChromeClient.FileChooserParams?
        ): Boolean {

            if (mFileChooserCallback !=null){
                mFileChooserCallback!!.onReceiveValue(null)
                mFileChooserCallback = null
            }

            mFileChooserCallback = filePathCallback
            takePicture()
            //return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
            return true
        }
    }


    override fun onBackPressed() {
        var webView: WebView = binding.webView
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            finish()
        }
    }

    inner class AndroidJavascriptInterface{
        @JavascriptInterface
        fun test(s:String){
            //toast(s)
        }
    }

    var PERMISSIONS = arrayOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.CAMERA
    )

    private fun checkPermission(): Boolean {
        for (i in PERMISSIONS.indices) {
            val result = ActivityCompat.checkSelfPermission(applicationContext, PERMISSIONS[i])
            if (result != PackageManager.PERMISSION_GRANTED) return false
        }
        return true
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CODE)
    }

    private val REQUEST_CODE = 1112
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> if (grantResults.size > 0) {

                var bResult = true
                grantResults.forEach {
                    if ( it != PackageManager.PERMISSION_GRANTED){
                        bResult = false
                        return@forEach
                    }}

                if ( bResult){
                    Toast.makeText(this, "모든 기능을 사용가능합니다.", Toast.LENGTH_LONG).show()
                    // [TODO] 퍼미션을 모두 수락해야만 수행가능하게 하려고 한다면
                    // 초기화 코드를 이곳에서 진행한다.
                    setUpUI()

                }  else{
                    Toast.makeText(this, "필수기능을 거부하셨습니다. 사용상 제약이 있습니다.", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            FILE_CHOOSE -> {
                mFileChooserCallback!!.onReceiveValue(
                    if (data == null)
                        null
                    else {
                        if (data!!.data == null) null
                        else arrayOf<Uri>(data!!.data as Uri)
                    }
                )
            }
        }
    }



}