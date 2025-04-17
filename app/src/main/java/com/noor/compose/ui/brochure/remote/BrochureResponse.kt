package com.noor.compose.ui.brochure.remote

data class BrochureResponse(
    val _embedded: Embedded?
) {
    data class Embedded(
        val contents: List<EmbeddedContent>
    )

    data class EmbeddedContent(
        val contentType: String,
        val content: List<BrochureContent>
    )

    data class BrochureContent(
        val contentId: String,
        val brochureImage: String?,
        val distance: Double,
        val publisher: BrochurePublisher
    )

    data class BrochurePublisher(
        val name: String,
    )
}
