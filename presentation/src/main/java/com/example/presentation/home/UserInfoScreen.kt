package com.example.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.domain.model.User
import com.example.presentation.HomeViewModel
import com.example.presentation.model.MashUpCrew

@SuppressLint("MutableCollectionMutableState")
@Composable
fun UserInfoScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    moveFollowerScreen: (userName: String) -> Unit
) {
    val context = LocalContext.current
    val userPages = viewModel.userPages.collectAsState().value

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
                CrewInfo(it.user, moveFollowerScreen)
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
private fun CrewInfo(
    user: User,
    moveFollowerScreen: (userName: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { moveFollowerScreen(user.name) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        StudyCrew(user.name, user.imageUrl)
        InfoText(
            text = "레포지토리 수",
            info = user.repoCnt.toString(),
            rowModifier = Modifier.padding(top = 5.dp),
        )
        InfoText(
            text = "블로그 링크",
            info = user.blogUrl,
            rowModifier = Modifier.padding(top = 5.dp),
        )
        InfoText(
            text = "팔로워 수",
            info = user.followerCnt.toString(),
            rowModifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
fun InfoText(
    text: String,
    info: String,
    rowModifier: Modifier = Modifier,
) {
    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "$text")
        Text(text = "$info")
    }
}