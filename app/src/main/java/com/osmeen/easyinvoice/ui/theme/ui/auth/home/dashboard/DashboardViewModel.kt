package com.osmeen.easyinvoice.ui.theme.ui.auth.home.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osmeen.easyinvoice.ui.theme.data.Resource
import com.osmeen.easyinvoice.ui.theme.data.home.DashboardRepository
import com.osmeen.easyinvoice.ui.theme.data.models.Dashboard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {

    private val _dashboard = MutableStateFlow<Resource<Dashboard>?>(null)
    val dashboard: StateFlow<Resource<Dashboard>?> = _dashboard

    init {
        getDashboardInfo()
    }

    private fun getDashboardInfo() = viewModelScope.launch {
        _dashboard.value = Resource.Loading
        _dashboard.value = repository.getDashboardInfo()
    }
}