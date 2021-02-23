package com.example.redditapp.ui.data

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

val API_KEY="YpkNB8rIXRK3tg"

interface RedditApiService {
    @GET("top.json")
    fun getPosts(
            @Query("limit")limit:Number
    ):Deferred<RedditPost>

    companion object    {
        operator fun invoke():RedditApiService{
            val requestInterceptor=Interceptor{
                chain ->
                val url =chain.request()
                        .url()
                        .newBuilder()
                        .addQueryParameter("key", API_KEY)
                        .build()
                val request=chain.request()
                        .newBuilder()
                        .url(url)
                        .build()
                return@Interceptor chain.proceed(request)
            }
            val okHTTPClient= OkHttpClient.Builder()
                    .addInterceptor(requestInterceptor)
                    .build()
            return Retrofit.Builder()
                    .client(okHTTPClient)
                    .baseUrl("https://www.reddit.com")
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RedditApiService::class.java)
        }
    }
}
