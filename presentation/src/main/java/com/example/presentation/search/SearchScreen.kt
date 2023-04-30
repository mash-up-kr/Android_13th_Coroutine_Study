package com.example.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.userInfo.StudyCrewFollowerItem
import model.User

/**
 * @Created by 김현국 2023/04/26
 * @Time 1:00 PM
 */

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    openUserInfo: (User) -> Unit,
) {
    val scrollState = rememberLazyListState()
    val shouldStartPaginate = remember {
        derivedStateOf {
            viewModel.canPaginate && (scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) >= (scrollState.layoutInfo.totalItemsCount - 6)
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value && viewModel.listState == ListState.IDLE) {
            viewModel.getUserListWithPage()
        }
    }
    val userInfoList = viewModel.userList
    SearchScreen(
        modifier = Modifier.fillMaxSize(),
        scrollState = scrollState,
        queryUserList = viewModel::get,
        users = userInfoList,
        openUserInfo = openUserInfo,
        listState = viewModel.listState,
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    queryUserList: (String) -> Unit,
    listState: ListState,
    scrollState: LazyListState,
    users: List<User>,
    openUserInfo: (User) -> Unit,
) {
    var query by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(query) {
        queryUserList(query)
    }
    Box {
        Column(
            modifier = modifier,
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = query,
                onValueChange = { query = it },
            )

            LazyColumn(
                modifier = Modifier,
                state = scrollState,

            ) {
                items(users) { user ->
                    StudyCrewFollowerItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(96.dp).clickable {
                                openUserInfo(user)
                            },

                        followerName = user.login ?: "",
                        imageUrl = user.avatarUrl ?: "",
                        gitHubList = user.url ?: "",
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp),
                    )
                }
            }
        }
        if (listState != ListState.IDLE) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}
