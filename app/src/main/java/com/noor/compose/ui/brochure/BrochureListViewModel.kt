package com.noor.compose.ui.brochure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noor.compose.ui.brochure.intent.BrochureIntent
import com.noor.compose.ui.brochure.remote.BrochureRepository
import com.noor.compose.ui.brochure.state.BrochureState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrochureListViewModel @Inject constructor(
    private val brochureRepository: BrochureRepository
) : ViewModel() {
    private val _state = MutableStateFlow(BrochureState())
    val brochureState: StateFlow<BrochureState> = _state

    init {
        onEvent(BrochureIntent.LoadBrochures)
    }

    fun onEvent(event: BrochureIntent) {
        when (event) {
            is BrochureIntent.LoadBrochures -> {
                loadBrochures()
            }
        }
    }

    private fun loadBrochures() {
        viewModelScope.launch {
            if (_state.value.isLoading) return@launch

            _state.value = _state.value.copy(isLoading = true)
            try {
                val brochures = brochureRepository.getBrochures()
                _state.value = _state.value.copy(isLoading = false, brochures = brochures, error = null)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message ?: "Something went wrong")
            }
        }
    }
}