package com.eespinor.stockmarketapp.domain.repository

import com.eespinor.stockmarketapp.domain.model.CompanyInfo
import com.eespinor.stockmarketapp.domain.model.CompanyListing
import com.eespinor.stockmarketapp.domain.model.IntradayInfo
import com.eespinor.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol:String
    ): Resource<List<IntradayInfo>>

    suspend fun getcompanyInfo(
        symbol: String
    ):Resource<CompanyInfo>
}