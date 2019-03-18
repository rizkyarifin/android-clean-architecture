package sample.base.app.di.module

import android.app.Application
import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sample.base.app.BuildConfig
import sample.base.app.data.network.AppService
import sample.base.app.data.network.interceptor.AppInterceptor
import sample.base.app.utils.Network
import sample.base.app.utils.ext.hasNetwork
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { createOkHttpClient(androidApplication()) }
    single { createRetrofit(get()) }
    single { createAppService(get()) }
}

fun createOkHttpClient(context: Application): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    clientBuilder.cache(Cache(context.cacheDir, (5 * 1024 * 1024).toLong()))
//        .addInterceptor(AppInterceptor(context = context))
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(context)!!)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 1).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            chain.proceed(request)
        }
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }
    return clientBuilder.build()
}

fun createRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(Network.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

fun createAppService(retrofit: Retrofit): AppService = retrofit.create(AppService::class.java)