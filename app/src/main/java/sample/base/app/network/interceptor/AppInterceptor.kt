package sample.base.app.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import sample.base.app.utils.Network

class AppInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val chainRequest = chain.request()
        val chainRequestURL = chainRequest.url()

        val url = chainRequestURL.newBuilder()
                .addQueryParameter("apiKey", Network.API_KEY)
                .build()

        val requestBuilder = chainRequest.newBuilder()
                .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}