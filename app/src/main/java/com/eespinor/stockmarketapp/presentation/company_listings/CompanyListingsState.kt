package com.eespinor.stockmarketapp.presentation.company_listings

import com.eespinor.stockmarketapp.domain.model.CompanyListing
import com.eespinor.stockmarketapp.util.Resource

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
