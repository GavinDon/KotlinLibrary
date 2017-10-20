package com.ln.kotlin.mylibrary.https

import android.content.Context
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * description: retrofit
 * Created by liNan on 2017/10/17 16:27

 */
class RetrofitClient private constructor() {

    var mRetrofit: Retrofit? = null
    private val DEFAULT_TIME_OUT = 10//超时时间
    private val DEFAULT_READ_TIME_OUT = 10

    init {
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        val httpLogging = HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.apply {
            connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)
            readTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
            retryOnConnectionFailure(true)//重连
            addInterceptor(httpLogging)
        }
        mRetrofit = Retrofit.Builder()
                .baseUrl(RetrofitHelper.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpBuilder.build())
                .build()
    }


    /**
     * 单例模式
     * 单例模式是一种非常有用的模式，而 Kotlin（继 Scala 之后）使单例声明变得很容易：
     */
    object singletonHolder {
            val INSTANCE = RetrofitClient()
    }

    /**
     * 静态对象
     */
    companion object {
        fun getInstance(context: Context): RetrofitClient {
            val instance: RetrofitClient = singletonHolder.INSTANCE
            return instance
        }

    }

    /**
     * 创建service
     */
    fun <T> create(clazz: Class<T>): T {
        return mRetrofit!!.create(clazz)
    }

    fun getRetrofit(): Retrofit {
        if (mRetrofit == null) Log.d("retrofitClient", "请先初始化retrofit")
        return mRetrofit!!
    }


}