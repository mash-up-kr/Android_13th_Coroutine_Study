package com.example.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.home.crew.StudyCrewList
import com.example.presentation.model.MashUpCrew

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/10
 */

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val textState = remember { mutableStateOf("") }
    val studyCrewList = MashUpCrew.values().toList()

    Column(modifier = modifier.padding(vertical = 8.dp)) {
        // 검색어 입력창
        TitleText(text = "Github 사용자를 검색해보세요")
        TextField(
            value = textState.value,
            onValueChange = { changedText -> textState.value = changedText },
            label = { Text("사용자 이름") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        // 즐겨찾기 (검색어 입력이 없을 때)
        if (textState.value.isEmpty()) {
            TitleText(text = "즐겨찾기 ✨")
            StudyCrewList(studyCrewList = studyCrewList, modifier = Modifier)
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
