package com.example.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.MainViewModel
import com.example.presentation.SearchUiState
import com.example.presentation.UiState
import com.example.presentation.common.MashUpDivider

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/10
 */
@Composable
fun Home(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val searchResult: UiState by viewModel.searchResult.collectAsState()
    val loading: Boolean by viewModel.loadingFlow.collectAsState()

    HomeScreen(
        loading = loading,
        searchResult = searchResult,
        onSearchIconClick = viewModel::searchUser
    )
}

@Composable
private fun HomeScreen(
    loading: Boolean,
    searchResult: UiState,
    onSearchIconClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (loading) {
        LoadingProgressBar()
    } else {
        Column(modifier = modifier) {
            SearchBar(onSearch = { onSearchIconClick(it) })

            when (searchResult) {
                is UiState.Initial -> {}
                is UiState.Success -> SearchResultContent(searchResult = searchResult.uiState)
                is UiState.Error ->
                    Toast.makeText(LocalContext.current, "삐용삐용", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun LoadingProgressBar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colors.primary
        )
        Text(text = "Loading...")
    }
}

@Composable
fun SearchResultContent(
    modifier: Modifier = Modifier,
    searchResult: List<SearchUiState>,
) {
    var selectedUserIndex by rememberSaveable {
        mutableStateOf(0)
    }
    UserList(userList = searchResult, onUserClick = { selectedUserIndex = it })
    MashUpDivider()

    if (selectedUserIndex != -1) {
        UserInfoContent(user = searchResult[selectedUserIndex])
    }
}

@Composable
fun UserInfoContent(
    user: SearchUiState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        item {
            UserInfo(
                modifier = Modifier.padding(16.dp),
                imageUrl = user.avatarUrl,
                userName = user.userName,
                blogUrl = user.blogUrl,
                repoCount = user.publicRepositoryCount
            )
        }
        item {
            FollowerHeader(modifier = Modifier, followers = user.followerCount)
        }
        items(user.followers) {
            FollowerContent(
                modifier = Modifier.padding(8.dp),
                imageUrl = it.avatarUrl,
                userName = it.userName
            )
            MashUpDivider()
        }
    }
}