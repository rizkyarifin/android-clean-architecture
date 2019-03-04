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

val networkModule = module {
    single { createOkHttpClient() }
    single { createRetrofit(get()) }
    single { createAppService(get()) }
}

fun createOkHttpClient(): OkHttpClient {
//        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
//            override fun getAcceptedIssuers(): Array<X509Certificate> {
//                return arrayOf()
//            }
//
//            @Throws(CertificateException::class)
//            override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
//            }
//
//            @Throws(CertificateException::class)
//            override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
//
//            }
//        })
//
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val okHttpClient = OkHttpClient.Builder()
//        okHttpClient.interceptors().add(loggingInterceptor)
//        okHttpClient.readTimeout(180, TimeUnit.SECONDS)
//        okHttpClient.connectTimeout(180, TimeUnit.SECONDS)
//
//
//        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
//        keyStore.load(null, null)
//
//        val sslContext = SSLContext.getInstance("TLS")
//
//        val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
//        trustManagerFactory.init(keyStore)
//        val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
//        keyManagerFactory.init(keyStore, "keystore_pass".toCharArray())
//        sslContext.init(null, trustAllCerts, SecureRandom())
//
//        okHttpClient.sslSocketFactory(sslContext.socketFactory)
//                .hostnameVerifier(object : HostnameVerifier {
//                    override fun verify(hostname: String, session: SSLSession): Boolean {
//                        return true
//                    }
//                })

    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    clientBuilder.addInterceptor(AppInterceptor())
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
