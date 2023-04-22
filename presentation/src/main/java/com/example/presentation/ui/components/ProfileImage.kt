package com.example.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.presentation.ui.theme.BasePink
import com.example.presentation.ui.theme.BasePurple

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GradientProfileImage(
    modifier: Modifier = Modifier,
    @StringRes userName: Int,
    @StringRes imageUrl: Int,
    size: Dp,
    borderWidth: Dp = 2.dp,
    startColor: Color = BasePurple,
    endColor: Color = BasePink,
) {
    GlideImage(
        model = stringResource(id = imageUrl),
        contentDescription = stringResource(id = userName),
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(
                width = borderWidth,
                brush = Brush.verticalGradient(
                    colors = listOf(startColor, endColor)
                ),
                shape = CircleShape
            )
    )
}
