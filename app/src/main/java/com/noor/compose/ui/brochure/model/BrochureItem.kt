package com.noor.compose.ui.brochure.model

import androidx.compose.runtime.Stable

@Stable
data class BrochureItem(
    val id: String,
    val contentType: String,
    val retailerName: String,
    val brochureImage: String?,
    val distance: Double
)
