package com.example.musicmapp2.data.network

import android.content.ContentValues.TAG
import android.util.Log
import com.example.musicmapp2.data.network.ConnectivityInterceptor
import com.example.musicmapp2.data.response.AlbumResponse
import com.example.musicmapp2.data.response.AlbumSearchResponse
import com.example.musicmapp2.data.response.TopAlbumsResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "2b635ea091494f2935f401fd5fb750bf"

//http://ws.audioscrobbler.com/2.0/
///2.0/?method=album.getinfo&api_key=2b635ea091494f2935f401fd5fb750bf&artist=Cher&album=Believe&format=json
//2.0/?method=album.search&album=believe&api_key=2b635ea091494f2935f401fd5fb750bf&format=json

interface ApiService {

    @GET("/2.0/?method=album.getinfo")
    fun getAlbumInformation(
        @Query("artist") artist: String,
        @Query("album") album: String
    ): Deferred<AlbumResponse>

    @GET("/2.0/?method=album.search")
    fun getAlbumSearch(
        @Query("album") album: String
    ): Deferred<AlbumSearchResponse>

    @GET("/2.0/?method=artist.gettopalbums")
    fun getTopAlbums(
        @Query("artist") artist: String,
    ): Deferred<TopAlbumsResponse>


    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApiService{
            val requestInterceptor = Interceptor{ chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .addQueryParameter("format", "json")
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                Log.i(TAG, "requestInterceptor " + request.toString())

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://ws.audioscrobbler.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }


}