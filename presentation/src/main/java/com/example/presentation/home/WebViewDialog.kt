package com.example.presentation.home

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun WebViewDialog(url: String, onDismiss: () -> Unit) {
    var isLoading by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    isLoading = false
                }
            }
        }
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when (event) {
                    Lifecycle.Event.ON_RESUME -> {
                        webView.onResume()
                        webView.resumeTimers()
                    }
                    Lifecycle.Event.ON_PAUSE -> {
                        webView.onPause()
                        webView.pauseTimers()
                    }
                    Lifecycle.Event.ON_DESTROY -> {
                        webView.destroy()
                    }
                    else -> {
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Dialog(onDismissRequest = {
        onDismiss()
    }) {
        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                if (isLoading) {
                    Text(
                        text = "Loading...",
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    AndroidView(
                        { webView },
                        Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

    LaunchedEffect(url) {
        webView.loadUrl(url)
    }
}
