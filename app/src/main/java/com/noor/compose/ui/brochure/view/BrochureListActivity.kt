package com.noor.compose.ui.brochure.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.noor.compose.ui.brochure.BrochureListViewModel
import com.noor.compose.ui.theme.MyAppTheme
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrochureListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                val viewModel: BrochureListViewModel = hiltViewModel()
                val state by viewModel.brochureState.collectAsState()

                BuildBrochureListScreen(state, viewModel::onEvent)
            }
        }
    }
}