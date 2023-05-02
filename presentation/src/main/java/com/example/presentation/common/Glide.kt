package com.example.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/29
 */

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun CircularImage(imageUrl: String, userName: String, size: Int = 96) {
    GlideImage(
        model = imageUrl,
        contentDescription = userName,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Blue, Color.Magenta)
                ),
                shape = CircleShape
            )
    )
}