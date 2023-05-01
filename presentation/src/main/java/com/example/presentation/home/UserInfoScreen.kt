package com.example.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.domain.model.User
import com.example.presentation.HomeViewModel
import com.example.presentation.model.MashUpCrew

@Composable
fun UserInfoScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    moveFollowerScreen: (userName: String) -> Unit
) {
    val context = LocalContext.current
    val userPages = viewModel.userPages.collectAsState().value

    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var url by rememberSaveable {
        mutableStateOf("")
    }

    val onShowDialog: (url: String) -> Unit = {
        showDialog = true
        url = it
    }

    if (showDialog) {
        WebViewDialog(url = url) {
            showDialog = false
        }
    }

    val studyCrewList = MashUpCrew.values().toList()

    Column(modifier = modifier.padding(8.dp)) {
        SearchBar { newValue ->
            viewModel.getUserPageInfo(newValue)
        }
        StudyCrewList(studyCrewList = studyCrewList, modifier = Modifier) { crew ->
            viewModel.getUserPageInfo(context.resources.getString(crew.userName))
        }

        LazyColumn {
            items(userPages) {
                UserInfo(it.user, onShowDialog, moveFollowerScreen)
            }
        }

    }
}

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    Row {
        TextField(
            value = searchQuery,
            onValueChange = { newValue ->
                searchQuery = newValue

            },
            label = { Text("Search") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },

            )
        Button(onClick = { onSearch(searchQuery) }) {
            Text(text = "Search")
        }
    }
}

@Composable
fun StudyCrewList(
    studyCrewList: List<MashUpCrew>,
    modifier: Modifier = Modifier,
    onClickUser: (crew: MashUpCrew) -> Unit,
) {
    LazyRow(modifier = modifier) {
        items(studyCrewList) {
            StudyCrew(
                userName = it.userName,
                imageUrl = it.profileImage,
                modifier = modifier.then(Modifier.clickable {
                    onClickUser(it)
                })
            )
        }
    }
}

@Composable
private fun UserInfo(
    user: User,
    onShowDialog: (url: String) -> Unit,
    moveFollowerScreen: (userName: String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clickable { moveFollowerScreen(user.name) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserImage(userName = user.name, imageUrl = user.imageUrl, modifier = Modifier.size(56.dp))
        Column(modifier = Modifier.padding(start = 5.dp)) {
            InfoText(
                text = "유저 이름",
                info = user.name,
                modifier = Modifier.padding(top = 2.dp),
            )
            InfoText(
                text = "레포지토리 수",
                info = user.repoCnt.toString(),
                modifier = Modifier.padding(top = 2.dp),
            )
            InfoText(
                text = "블로그 링크",
                info = user.blogUrl,
                modifier = Modifier
                    .padding(top = 2.dp)
                    .clickable {
                        onShowDialog(user.blogUrl)
                    },
                secondTextStyle = TextStyle(
                    color = MaterialTheme.colors.primary,
                    textDecoration = TextDecoration.Underline
                )
            )
            InfoText(
                text = "팔로워 수",
                info = user.followerCnt.toString(),
                modifier = Modifier.padding(top = 2.dp),
            )
        }
    }
}

@Composable
fun InfoText(
    text: String,
    info: String,
    modifier: Modifier = Modifier,
    spacerModifier: Modifier = Modifier.padding(5.dp),
    secondTextStyle: TextStyle = TextStyle()
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "$text")
        Spacer(modifier = spacerModifier)
        Text(text = "$info", style = secondTextStyle)

    }
}