package com.bankmtk.pictureoftheday.ui.main

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
}

data class ServerResponse(
val explanation:String?,
val url: String?
)
sealed class PictureOfTheDayData{
    data class Success(val serverResponse: ServerResponse):PictureOfTheDayData()
    data class Error(val error:Throwable):PictureOfTheDayData()
    data class Loading(val progress: Int?):PictureOfTheDayData()
}
interface PictureOfTheDayApi{
    @GET("planetary/apod")
    fun getPictureOfTheday(@Query("api_key")apiKey:String):Call<ServerResponse>
}
class PODRetrofitImpl{
    private val baseUrl = "https://api.nasa.gov/"

    fun getRetrofitImpl(): PictureOfTheDayApi{
        val podRetrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(createOkHttpClient(PODInterceptor()))
            .build()
        return podRetrofit.create(PictureOfTheDayApi::class.java)
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient{
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }


    inner class PODInterceptor: Interceptor{
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request())
        }
    }
}
