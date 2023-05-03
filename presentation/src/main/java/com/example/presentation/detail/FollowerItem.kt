package com.example.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.model.UserModel

@Composable
fun FollowerItem(modifier: Modifier = Modifier, user: UserModel) {
    Column(modifier = modifier) {
        Text(text = user.name)
        Divider(thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
    }
}
