package sample.base.app.data.network.interceptor

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import sample.base.app.utils.Network
import sample.base.app.utils.ext.hasNetwork

class AppInterceptor(val context: Application) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var chainRequest = chain.request()
//        val chainRequestURL = chainRequest.url()

//        val url = chainRequestURL.newBuilder()
//            .addQueryParameter("apiKey", Network.API_KEY)
//            .build()

//        val requestBuilder = chainRequest.newBuilder()
//            .url(url)

        chainRequest =  if (hasNetwork(context)!!) {
            Log.d("hubla", "network")
            chainRequest.newBuilder().header("Cache-Control", "public, max-age=" + 1).build()
        }
        else {
            Log.d("hubla", "cache")
            chainRequest.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
        }

        return chain.proceed(chainRequest)
    }

}