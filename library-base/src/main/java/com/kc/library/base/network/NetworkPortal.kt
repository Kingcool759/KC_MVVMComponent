package com.kc.library.base.network


import com.example.mykotlindemo.utils.HttpLogger
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*


/**
 * @data on 2020/9/27 2:35 PM
 * @auther KC
 * @describe
 */
object NetworkPortal {
    //网络请求
    private fun getDefaultBuilder(): OkHttpClient.Builder {
        // 创建一个OkHttpClient
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLogger()).setLevel(HttpLoggingInterceptor.Level.BODY))
        createHostnameVerifier()?.let { builder.hostnameVerifier(it) }
        /* json 拦截 */
        createSSlSocket()?.let {
            builder.sslSocketFactory(it, object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    x509Certificates: Array<X509Certificate>,
                    s: String
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    x509Certificates: Array<X509Certificate>,
                    s: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate?> {
                    return arrayOfNulls(0)
                }
            })
        }
        return builder
    }

    private fun buildRetrofit(url: String): Retrofit? {
        val builder: OkHttpClient.Builder = getDefaultBuilder()
        builder.retryOnConnectionFailure(false)
        return Retrofit.Builder()
            .baseUrl(url)
            .client(builder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * @return 指定service实例
     */
    fun <T> getService(clazz: Class<T>?): T? {
        return getService(clazz, "")
    }

    /**
     * @return 指定service实例
     */
    fun <T> getService(clazz: Class<T>?, baseURL: String): T? {
        var service: T? = null
        if (baseURL.isEmpty()) {
            service = buildRetrofit(AppApi.BaseURL)?.create(clazz)
        }else{
            service = buildRetrofit(baseURL)?.create(clazz)
        }
        return service
    }

    //抓包用，Https(默认只能抓到http,所以需要用代码设置)
    private fun createHostnameVerifier(): HostnameVerifier? {
        return HostnameVerifier { s, sslSession -> true }
    }

    private fun createSSlSocket(): SSLSocketFactory? {
        var ssl: SSLContext? = null
        try {
            ssl = SSLContext.getInstance("SSL")
            ssl.init(
                null,
                arrayOf<TrustManager>(object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        x509Certificates: Array<X509Certificate>,
                        s: String
                    ) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        x509Certificates: Array<X509Certificate>,
                        s: String
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate?> {
                        return arrayOfNulls(0)
                    }
                }),
                SecureRandom()
            )
            return ssl.socketFactory
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }
        return null
    }
}