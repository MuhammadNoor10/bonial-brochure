package com.noor.compose.ui.brochure.remote

import retrofit2.http.GET

interface BrochureApi {
    @GET("shelf.json")
    suspend fun getBrochures(): BrochureResponse
}
