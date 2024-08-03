package com.seosh817.kakaoimagesearch.core.data.remote.okhttp.interceptor

import com.seosh817.kakaoimagesearch.core.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CookiesInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()

        val newRequest = originalRequest
            .newBuilder()
            .addHeader(AUTHORIZATION, "$KEY_PREFIX ${BuildConfig.KAKAO_API_KEY}")
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val KEY_PREFIX = "KakaoAK"
    }
}