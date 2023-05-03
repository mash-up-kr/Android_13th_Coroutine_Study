package com.example.presentation.home.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.presentation.ui.theme.BasePink
import com.example.presentation.ui.theme.BasePurple

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CircleProfile(
    modifier: Modifier = Modifier,
    userName: String,
    imageUrl: String,
    size: Int = 96,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        GlideImage(
            model = imageUrl,
            contentDescription = userName,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(size.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp, brush = Brush.verticalGradient(
                        colors = listOf(BasePurple, BasePink)
                    ), shape = CircleShape
                )
        )

        Text(
            text = userName,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.paddingFromBaseline(top = 24.dp,),
            textAlign = TextAlign.Center
        )
    }
}
