package com.ln.kotlin.mylibrary.https

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * description: 构建retrofit 使用单例模式
 * Created by liNan on 2017/6/23 15:49

 */
object RetrofitHelper {

    const  val BASE_URL:String="http://v.juhe.cn/"
    private  var mRetrofit:Retrofit

    init {
       val okHttpBuilder:OkHttpClient.Builder= OkHttpClient.Builder()
        okHttpBuilder.connectTimeout(10,TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(10,TimeUnit.SECONDS)
        okHttpBuilder.retryOnConnectionFailure(true)//重连
        val httpLogging=HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor (httpLogging)
        mRetrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpBuilder.build())
                .build()


    }
    fun getRetrofit():Retrofit{
        return  mRetrofit
    }

//    /**
//     * 获取APIS对象
//     * @return
//     */
//    fun create(): APIS {
//        //使用APIS
//        return mRetrofit.create(APIS::class.java)
//    }

}