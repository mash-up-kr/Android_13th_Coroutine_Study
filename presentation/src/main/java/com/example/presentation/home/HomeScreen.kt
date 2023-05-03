package com.example.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.DetailActivity
import com.example.presentation.MainViewModel
import com.example.presentation.home.crew.StudyCrewList
import com.example.presentation.home.user.UserRowItem
import com.example.presentation.model.MashUpCrew
import com.example.presentation.model.SearchState
import com.example.presentation.model.UserInfoModel
import com.example.presentation.ui.theme.BasePurple

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/10
 */

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = viewModel()

    val keyword = viewModel.keyword.collectAsState()
    val searchState = viewModel.searchState.collectAsState()
    val studyCrewList = MashUpCrew.values().toList()

    Column(modifier = modifier.padding(vertical = 8.dp)) {
        // 검색어 입력창
        TitleText(text = "Github 사용자를 검색해보세요")
        TextField(
            value = keyword.value,
            onValueChange = { changedText -> viewModel.keyword.value = changedText },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text("사용자 이름") },
            singleLine = true
        )

        // 즐겨찾기 (검색어 입력이 없을 때)
        if (keyword.value.isEmpty()) {
            TitleText(text = "즐겨찾기 ✨")
            StudyCrewList(studyCrewList = studyCrewList, modifier = Modifier)
        } else {
            TitleText(text = "검색결과 🥨")
            when (searchState.value) {
                is SearchState.Loading -> LoadingContent(Modifier.fillMaxSize())
                is SearchState.Success -> SuccessContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp),
                    searchResult = (searchState.value as SearchState.Success).result
                )
                is SearchState.Error -> ErrorContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp),
                    errorMessage = (searchState.value as SearchState.Error).message
                )
            }
        }
    }
}

@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(8.dp),
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 18.sp
    )
}

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.padding(vertical = 20.dp), color = BasePurple)
        Text(text = "Loading...")
    }
}

@Composable
fun SuccessContent(modifier: Modifier = Modifier, searchResult: List<UserInfoModel>) {
    val context = LocalContext.current

    LazyColumn(modifier = modifier) {
        items(searchResult) {
            UserRowItem(modifier = Modifier.fillMaxWidth(), item = it, onItemClick = {
                if (it.followerList.isEmpty()) {
                    Toast.makeText(context, "팔로워가 없습니다", Toast.LENGTH_SHORT).show()
                } else {
                    DetailActivity.getIntent(context, it).also { intent ->
                        context.startActivity(intent)
                    }
                }
            })

            Divider(thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun ErrorContent(modifier: Modifier = Modifier, errorMessage: String) {
    Text(text = errorMessage, modifier = modifier, textAlign = TextAlign.Center)
}
