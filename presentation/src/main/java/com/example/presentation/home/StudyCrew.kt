package com.example.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.SearchUiState
import com.example.presentation.common.CircularImage

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/25
 */

@Composable
fun User(
    userName: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
    ) {
        CircularImage(imageUrl = imageUrl, userName = userName)
        Text(
            text = userName,
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            modifier = Modifier.paddingFromBaseline(top = 24.dp)
        )
    }
}

@Composable
fun UserList(
    userList: List<SearchUiState>,
    modifier: Modifier = Modifier,
    onUserClick: (Int) -> Unit
) {
    LazyRow(modifier = modifier) {
        itemsIndexed(userList) { index, user ->
            User(
                userName = user.userName,
                imageUrl = user.avatarUrl,
                modifier = modifier.clickable { onUserClick(index) }
            )
        }
    }
}