package com.noor.compose.remote

import com.google.gson.GsonBuilder
import com.noor.compose.ui.brochure.remote.BrochureApi
import com.noor.compose.ui.brochure.remote.BrochureResponse
import com.noor.compose.ui.brochure.remote.BrochureResponseDeserializer
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RemoteProviders @Inject constructor() {

    private val client by lazy {
        OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(60, 120, TimeUnit.SECONDS))
            .build()
    }

    val gson = GsonBuilder()
        .registerTypeAdapter(BrochureResponse.EmbeddedContent::class.java, BrochureResponseDeserializer())
        .create()

    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://mobile-s3-test-assets.aws-sdlc-bonial.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addCallAdapterFactory(ResultCallAdapterFactory())
            .client(client)
            .build()
    }

    val brochureApi: BrochureApi by lazy {
        retrofit.create(BrochureApi::class.java)
    }
}