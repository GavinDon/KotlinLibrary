package com.ln.kotlin.kotlinlibrary.https

import com.ln.kotlin.kotlinlibrary.bean.NewsBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * description:
 * Created by liNan on 2017/7/4 14:44

 */
interface APIS {
    @GET("toutiao/index")
    fun news(@Query("key") key:String,@Query("type") type:String) : Observable<NewsBean>

}