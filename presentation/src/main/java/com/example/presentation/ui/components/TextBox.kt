package com.example.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.ui.theme.BasePink
import com.example.presentation.ui.theme.BasePurple

@Composable
fun GradientTextBox(
    modifier: Modifier = Modifier,
    borderWidth: Dp = 2.dp,
    count: Int = -1,
    startColor: Color = BasePurple,
    endColor: Color = BasePink,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = borderWidth,
                brush = Brush.verticalGradient(
                    colors = listOf(startColor, endColor)
                ),
                shape = RectangleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.gradient_text_box_head)
                    + count
                    + stringResource(id = R.string.gradient_text_box_tail), //
            style = MaterialTheme.typography.body2,
            fontSize = 15.sp,
            fontWeight = FontWeight(700),
            maxLines = 1,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}

@Preview
@Composable
private fun GradientTextBoxPreview() {
    GradientTextBox(borderWidth = 2.dp)
}