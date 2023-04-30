package com.example.presentation.detail

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.presentation.model.UserModel

@Composable
fun FollowerItem(modifier: Modifier = Modifier, user: UserModel) {
    Row(modifier = modifier) {
        Text(text = user.toString())
    }
}
