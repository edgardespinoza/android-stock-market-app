package com.eespinor.stockmarketapp.domain.model

import android.content.ClipDescription
import com.squareup.moshi.Json

data class CompanyInfo(
    val symbol: String,
    val description: String,
    val name: String,
    val country: String,
    val industry: String
)