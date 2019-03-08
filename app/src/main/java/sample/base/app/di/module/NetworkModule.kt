package sample.base.app.di.module

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sample.base.app.BuildConfig
import sample.base.app.data.network.AppService
import sample.base.app.data.network.interceptor.AppInterceptor
import sample.base.app.utils.Network
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { createOkHttpClient() }
    single { createRetrofit(get()) }
    single { createAppService(get()) }
}

fun createOkHttpClient(): OkHttpClient {

    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    clientBuilder.addInterceptor(AppInterceptor())
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
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