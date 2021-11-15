package com.example.zeytunpharma.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zeytunpharma.Graph
import com.example.zeytunpharma.R
import com.example.zeytunpharma.data.HomeViewModel
import com.example.zeytunpharma.data.OrderStore
import com.example.zeytunpharma.data.OrderToClient
import com.example.zeytunpharma.util.mirroringBackIcon
import com.google.accompanist.insets.statusBarsPadding

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeWithSearch(
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    state: SearchState = rememberSearchState(),
) {

    val viewModel: HomeViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()
    val ordersStore: OrderStore = Graph.orderStore
    Log.e("HOME WITH SEARCH", "ENTERED")
    Surface(modifier = modifier.fillMaxSize()) {
        Column {

            Spacer(modifier = Modifier.statusBarsPadding())
            SearchBar(
                query = state.query,
                onQueryChange = { state.query = it },
                searchFocused = state.focused,
                onSearchFocusChange = { state.focused = it },
                onClearQuery = { state.query = TextFieldValue("") },
                searching = state.searching
            )
            LaunchedEffect(state.query.text, viewState.selectedHomeTab) {
                state.searching = true
                state.searchResults =
                    ordersStore.getOrdersByTabAndSearchText(viewState.selectedHomeTab,
                        state.query.text)
                state.searching = false
            }
            Log.e("state.query.text ", state.query.text + "******")
            Log.e("HOME WITHSEARCH ", viewState.selectedHomeTab.toString())
            Log.e("HOME SearchResult ", state.searchResults.toString())

            SearchResults(
                orderToClient = if (state.query.text != "") state.searchResults else viewState.orderToClients,
                modifier = Modifier.fillMaxSize(),
                homeTabs = viewState.homeTabs,
                selectedHomeTab = viewState.selectedHomeTab,
                onHomeTabSelected = viewModel::onHomeTabSelected,
                navigateToDetail = navigateToDetail,
            )

        }
    }
}

@Composable
private fun SearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    searchFocused: Boolean,
    onSearchFocusChange: (Boolean) -> Unit,
    onClearQuery: () -> Unit,
    searching: Boolean,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Log.e("SearchBar", "Entered")
        Box(Modifier.fillMaxSize()) {
            if (query.text.isEmpty()) {
                SearchHint()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            ) {
                Log.e("Row", "Entered")
                if (searchFocused) {
                    IconButton(onClick = onClearQuery) {
                        Icon(
                            imageVector = mirroringBackIcon(),
                            tint = MaterialTheme.colors.primaryVariant,
                            contentDescription = stringResource(R.string.label_back)
                        )
                    }
                }
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged {
                            onSearchFocusChange(it.isFocused)
                        }
                )
                if (searching) {
                    Log.e("SearchingIndicator", "Entered")
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(36.dp)
                    )
                } else {
                    Spacer(Modifier.width(IconSize)) // balance arrow icon
                }
            }
        }
    }
}

private val IconSize = 48.dp

@Composable
private fun SearchHint() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Log.e("SearchHint ", "Entered")
        Icon(
            imageVector = Icons.Outlined.Search,
            tint = MaterialTheme.colors.onPrimary,
            contentDescription = stringResource(R.string.label_search)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = stringResource(R.string.search_order),
            color = MaterialTheme.colors.onPrimary
        )
    }
}

enum class SearchDisplay {
    Results, NoResults
}

@Composable
private fun rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    focused: Boolean = false,
    searching: Boolean = false,
    searchResults: List<OrderToClient> = emptyList(),

    ): SearchState {
    return remember {
        SearchState(
            query = query,
            focused = focused,
            searching = searching,
            searchResults = searchResults,
        )
    }
}

@Stable
class SearchState(
    query: TextFieldValue,
    focused: Boolean,
    searching: Boolean,
    searchResults: List<OrderToClient>,
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    var searching by mutableStateOf(searching)

    var searchResults by mutableStateOf(searchResults)
    val searchDisplay: SearchDisplay
        get() = when {
            searchResults.isEmpty() -> SearchDisplay.NoResults
            else -> SearchDisplay.Results
        }
}