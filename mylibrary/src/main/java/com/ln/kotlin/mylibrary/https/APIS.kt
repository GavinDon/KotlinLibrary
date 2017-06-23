package com.ln.kotlin.mylibrary.https

import com.ln.kotlin.kotlinlibrary.entity.LoginData
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part


/**
 * description:
 * Created by liNan on 2017/6/23 16:42

 */
interface  APIS {

    @Multipart
    @PUT("user/photo")
    fun updateUser(@Part("photo") photo: RequestBody, @Part("description") description: RequestBody): Call<LoginData>


}