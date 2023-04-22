package com.example.presentation.home

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.model.MashUpCrew
import com.example.presentation.ui.components.GradientProfileImage
import com.example.presentation.ui.components.HorizontalDivider

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/10
 */

/**
 * @author 재성
 * XML로 하실 분은 띠용하지 마시고 가볍게 삭제하시길
 * Compose로 하실 분들은 띠용하시고 이어서 작성하셔도 됩니당
 */
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val studyCrewList = MashUpCrew.values().toList()
    Column(modifier = modifier) {
        Spacer(Modifier.height(19.dp))
        StudyCrewList(studyCrewList = studyCrewList)
        HorizontalDivider(modifier = Modifier.padding(top = 16.dp))
    }
}

@Composable
private fun StudyCrewList(
    modifier: Modifier = Modifier,
    studyCrewList: List<MashUpCrew>,
) {
    var currentSelect by remember { mutableStateOf(studyCrewList[0].userName) }

    LazyRow(modifier = modifier) {
        items(studyCrewList) { mashUpCrew ->
            StudyCrew(
                modifier = modifier,
                userName = mashUpCrew.userName,
                imageUrl = mashUpCrew.profileImage,
                currentSelect = currentSelect,
                onSelectChange = {
                    currentSelect = mashUpCrew.userName
                }
            )
        }
    }
}

@Composable
private fun StudyCrew(
    modifier: Modifier = Modifier,
    @StringRes userName: Int,
    @StringRes imageUrl: Int,
    currentSelect: Int,
    onSelectChange: () -> Unit,
) {
    val isSelected = currentSelect == userName

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clickable(onClick = onSelectChange)
            .background(if (isSelected) LightGray else Transparent)
    ) {
        GradientProfileImage(
            modifier = Modifier,
            userName = userName,
            imageUrl = imageUrl,
            size = 96.dp,
        )

        Text(
            text = stringResource(id = userName),
            style = MaterialTheme.typography.body2,
            fontSize = 15.sp,
            fontWeight = FontWeight(700),
            maxLines = 1,
            modifier = Modifier.paddingFromBaseline(top = 24.dp)
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}