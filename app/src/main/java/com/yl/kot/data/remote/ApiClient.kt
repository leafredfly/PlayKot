package com.yl.kot.data.remote

import com.yl.kot.data.remote.convert.ConverterFactory
import com.yl.kot.data.remote.interceptor.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit


/**
 * Author: Want-Sleep
 * Date: 2018/07/25
 * Desc:
 */
class ApiClient private constructor() {

    companion object {
        private val INSTANCE: ApiClient by lazy {
            ApiClient()
        }

        private const val BASE_URL = "https://www.wanandroid.com/"

        fun getApiService(): ApiService {
            return INSTANCE.mApiService
        }
    }

    private var mRetrofit: Retrofit
    private var mApiService: ApiService

    init {
        mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(buildOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ConverterFactory.create())
                .build()
        mApiService = mRetrofit.create(ApiService::class.java)
    }

    private fun buildOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor())
                .build()
    }
}
