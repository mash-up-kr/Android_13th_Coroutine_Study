package com.example.presentation.home

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserImage(imageUrl: String, userName: String, modifier: Modifier = Modifier.size(96.dp)) {
    GlideImage(
        model = imageUrl,
        contentDescription = userName,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .clip(CircleShape)
            .border(
                width = 1.dp, brush = Brush.verticalGradient(
                    colors = listOf(Color.Blue, Color.Gray)
                ), shape = CircleShape
            )
    )
}

@Composable
fun StudyCrew(
    userName: String, imageUrl: String, modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier
            .padding(8.dp)
            .padding(top = 10.dp)
    ) {
        UserImage(imageUrl, userName)
        Text(
            text = userName,
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            modifier = Modifier.paddingFromBaseline(top = 24.dp)
        )
    }
}

@Composable
fun StudyCrew(
    @StringRes userName: Int, @StringRes imageUrl: Int, modifier: Modifier = Modifier
) {
    StudyCrew(
        userName = stringResource(userName),
        imageUrl = stringResource(imageUrl),
        modifier = modifier
    )
}
