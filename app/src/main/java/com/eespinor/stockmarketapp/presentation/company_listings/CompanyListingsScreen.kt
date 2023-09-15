
package com.eespinor.stockmarketapp.presentation.company_listings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eespinor.stockmarketapp.presentation.destinations.CompanyInfoScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
@Destination(start = true)
fun CompanyListingScreen(
    navigator: DestinationsNavigator,
    viewModel: CompanyListingsViewModel = hiltViewModel()
) {
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(viewModel.state.isRefreshing) }


    fun refresh() = refreshScope.launch {
        refreshing = true
        viewModel.onEvent(CompanyListingsEvent.Refresh)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = viewModel.state.searchQuery,
            onValueChange = {
                viewModel.onEvent(
                    CompanyListingsEvent.OnSearchQueryChange(it)
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search ...")
            },
            maxLines = 1,
            singleLine = true
        )

        Box(modifier = Modifier.pullRefresh(state)){
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ){
                if(!refreshing){
                    items(viewModel.state.companies.size) { i ->
                        val company = viewModel.state.companies[i]
                        CompanyItem(
                            company = company,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navigator.navigate(
                                         CompanyInfoScreenDestination(company.symbol)
                                    )
                                }
                                .padding(16.dp)
                        )
                        if(i < viewModel.state.companies.size) {
                            Divider(modifier = Modifier.padding(
                                horizontal = 16.dp
                            ))
                        }
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = refreshing,
                state = state,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }

    }
}