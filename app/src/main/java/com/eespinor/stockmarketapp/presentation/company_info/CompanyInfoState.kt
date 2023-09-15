package com.eespinor.stockmarketapp.presentation.company_info

import com.eespinor.stockmarketapp.domain.model.CompanyInfo
import com.eespinor.stockmarketapp.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
