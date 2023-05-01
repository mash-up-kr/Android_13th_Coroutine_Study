package com.example.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.R
import com.example.presentation.model.UserInfo
import com.example.presentation.ui.components.GradientProfileImage
import com.example.presentation.ui.components.HorizontalDivider
import com.example.presentation.ui.theme.GitHubLink

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/10
 */

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onClick: (Int) -> Unit,
) {
    HomeScreen(
        modifier = modifier,
        viewModel = viewModel,
        onClick = onClick,
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onClick: (Int) -> Unit,
) {
    val userResults by viewModel.userResults.collectAsState()

    Column(modifier = modifier) {
        SearchBar(onClick = viewModel::searchUsers)
        HorizontalDivider()
        SearchResult(
            userResults = userResults,
            onClick = onClick,
//            navController = navController,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        modifier = modifier.height(IntrinsicSize.Max),
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current

        TextField(
            value = textFieldValue,
            modifier = Modifier
                .weight(0.85f)
                .fillMaxHeight(),
            onValueChange = { newTextFieldValue ->
                textFieldValue = newTextFieldValue
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_bar_hint),
                    color = LightGray
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = White
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onClick(textFieldValue.text)
                keyboardController?.hide()
                focusManager.clearFocus()
            })
        )

        SearchButton(
            modifier = Modifier.weight(0.15f),
            onClick = { onClick(textFieldValue.text) }
        )
    }
}

@Composable
private fun SearchButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier
            .background(Gray)
            .fillMaxHeight(),
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            tint = White,
            contentDescription = ""
        )
    }
}

@Composable
private fun SearchResult(
    modifier: Modifier = Modifier,
    userResults: List<UserInfo>,
    onClick: (Int) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(userResults) { index, userResult ->
            SearchItem(
                userName = userResult.user.login,
                followerCount = userResult.user.followerCount,
                imageUrl = userResult.user.avatarUrl,
                onClick = {
                    onClick(index)
                }
            )

            Spacer(modifier = Modifier.height(7.dp))
            HorizontalDivider()
        }
    }
}

@Composable
private fun SearchItem(
    modifier: Modifier = Modifier,
    userName: String,
    followerCount: Int,
    imageUrl: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        GradientProfileImage(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .padding(start = 18.dp),
            userName = userName,
            imageUrl = imageUrl,
            size = 72.dp,
            borderWidth = 1.dp,
        )

        Column(modifier = Modifier.padding(start = 27.dp)) {
            Text(
                modifier = Modifier.padding(top = 26.dp),
                text = userName,
                style = MaterialTheme.typography.body2,
                fontSize = 15.sp,
                fontWeight = FontWeight(700),
                maxLines = 1,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier
                    .padding(bottom = 23.dp)
                    .clickable(onClick = {

                    }),
                text = stringResource(id = R.string.gradient_text_box_head) +
                        followerCount +
                        stringResource(id = R.string.gradient_text_box_tail),
                style = MaterialTheme.typography.body2,
                color = GitHubLink,
                fontSize = 15.sp,
                fontWeight = FontWeight(500),
                maxLines = 1,
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeRoute {}
}
