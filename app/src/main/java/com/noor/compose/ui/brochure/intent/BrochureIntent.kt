package com.noor.compose.ui.brochure.intent

sealed class BrochureIntent {
    data object LoadBrochures : BrochureIntent()
}