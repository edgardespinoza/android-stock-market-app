package com.eespinor.stockmarketapp.di

import com.eespinor.stockmarketapp.data.csv.CSVParser
import com.eespinor.stockmarketapp.data.csv.CompanyListingParser
import com.eespinor.stockmarketapp.data.csv.IntradayInfoParser
import com.eespinor.stockmarketapp.data.repository.StockRepositoryImpl
import com.eespinor.stockmarketapp.domain.model.CompanyListing
import com.eespinor.stockmarketapp.domain.model.IntradayInfo
import com.eespinor.stockmarketapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingParser(
        companyListingParser: CompanyListingParser
    ):CSVParser<CompanyListing>


    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ):CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ):StockRepository

}