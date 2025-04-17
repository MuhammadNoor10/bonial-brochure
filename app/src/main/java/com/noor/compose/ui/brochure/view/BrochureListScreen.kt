package com.noor.compose.ui.brochure.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.noor.compose.R
import com.noor.compose.ui.brochure.intent.BrochureIntent
import com.noor.compose.ui.brochure.model.BrochureItem
import com.noor.compose.ui.brochure.state.BrochureState
import com.noor.compose.ui.components.ProgressIndicatorCircle
import com.noor.compose.ui.theme.customTextColors

@Composable
fun BuildBrochureListScreen(state: BrochureState, onEvent: (BrochureIntent) -> Unit) {
    if (state.isLoading) {
        ProgressIndicatorCircle()
    } else if (state.error != null) {
        Text(text = "Error: ${state.error}", color = MaterialTheme.customTextColors.primary)
    } else {
        val configuration = LocalConfiguration.current
        val columns = when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> 3
            else -> 2
        }

        BrochureGrid(brochures = state.brochures, columns = columns)
    }
}

@Composable
fun BrochureGrid(brochures: List<BrochureItem>, columns: Int) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(brochures, key = { it.id }, span = { item ->
            when (item.contentType) {
                "brochurePremium" -> GridItemSpan(columns)
                else -> GridItemSpan(1)
            }
        }) { item ->
            BrochureCard(item)
        }
    }
}

@Composable
fun BrochureCard(item: BrochureItem) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(),
    ) {
        Column() {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.brochureImage)
                    .crossfade(true)
                    .placeholder(R.drawable.ic_error_outline)
                    .build(),
                contentDescription = null,
                loading = {
                    //handle loading if required
                },
                error = {
                    //handle error if required
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Text(text = item.retailerName,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    MaterialTheme {
        BuildBrochureListScreen(
            BrochureState(listOf(
                BrochureItem(id = "1", contentType = "brochure", retailerName = "Retailer 1", brochureImage = "https://content-media.bonial.biz/af609f14-28b5-40d0-aa11-3bc4c45792ac/preview.jpg", distance = 1.5),
                BrochureItem(id = "2", contentType = "brochurePremium", retailerName = "Retailer 2", brochureImage = "https://content-media.bonial.biz/af609f14-28b5-40d0-aa11-3bc4c45792ac/preview.jpg", distance = 2.5),
            )),
            {}
        )
    }
}