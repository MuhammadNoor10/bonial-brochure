package com.noor.compose.ui.brochure.state

import com.noor.compose.ui.brochure.model.BrochureItem

data class BrochureState(
    val brochures: List<BrochureItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
