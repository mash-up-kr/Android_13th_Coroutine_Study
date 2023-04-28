package com.example.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/28
 */

@Composable
fun MashUpDivider(modifier: Modifier = Modifier) {
    Divider(
        thickness = 1.dp,
        modifier = Modifier.fillMaxWidth()
    )
}