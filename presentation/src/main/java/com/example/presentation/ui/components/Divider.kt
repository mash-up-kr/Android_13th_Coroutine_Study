package com.example.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.theme.DividerGray

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = DividerGray,
    thickness: Dp = 1.dp,
    verticalPadding: Dp = 0.dp
) {
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
            .padding(vertical = verticalPadding),
        color = color
    )
}