package com.noor.compose.ui.brochure.remote

import com.noor.compose.remote.RemoteProviders
import com.noor.compose.ui.brochure.model.BrochureItem
import javax.inject.Inject

class BrochureRepository @Inject constructor(
    private val remoteProviders: RemoteProviders
) {
    suspend fun getBrochures(): List<BrochureItem> {
        return remoteProviders.brochureApi.getBrochures()._embedded?.contents.orEmpty()
            .filter { it.contentType == "brochure" || it.contentType == "brochurePremium" }
            .flatMap { container ->
                container.content.filter { it.distance <= 5 }
                    .map { content ->
                        BrochureItem(
                            id = content.contentId,
                            contentType = container.contentType,
                            retailerName = content.publisher.name,
                            brochureImage = content.brochureImage,
                            distance = content.distance
                        )
                    }
            }
    }
}