package com.afshinshahriarifahliani.marvel_characters.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.afshinshahriarifahliani.marvel_characters.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.chromium.base.Log
import java.math.BigInteger
import java.security.MessageDigest
const val LIMIT = 20
const val OFFSET= 0

//hash - a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)
fun digest(ts: String, apiKey: String, priKey: String): String {
    val text = (ts + priKey + apiKey).toByteArray()
    val crypt = MessageDigest.getInstance("MD5")
    crypt.update(text)
    return BigInteger(1, crypt.digest()).toString(16).apply {
        Log.i("CRYPT", this)
    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
    }

/**
 * This function check if there is any kind of networks available
 * @param context
 * @return boolean
 */
@Suppress("DEPRECATION")
 fun isNetworkAvailable(context: Context?):Boolean{
    if (context == null) return false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            return true
        }
    }
    return false
}

fun getCircularProgress(context: Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=10f
        centerRadius=50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, circularProgressDrawable: CircularProgressDrawable){
    val options=RequestOptions()
        .placeholder(circularProgressDrawable)
        .error(R.drawable.no_image)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}