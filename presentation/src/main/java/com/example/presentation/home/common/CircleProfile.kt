package com.example.presentation.home.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CircleProfile(
    userName: String,
    imageUrl: String,
) {
    GlideImage(
        model = imageUrl,
        contentDescription = userName,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(96.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp, brush = Brush.verticalGradient(
                    colors = listOf(Color.Blue, Color.Gray)
                ), shape = CircleShape
            )
    )

    Text(
        text = userName,
        style = MaterialTheme.typography.body2,
        maxLines = 1,
        modifier = Modifier.paddingFromBaseline(top = 24.dp)
    )
}
