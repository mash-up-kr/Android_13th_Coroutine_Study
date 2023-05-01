package com.example.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import com.example.domain.model.Follower
import com.example.presentation.HomeViewModel

@Composable
fun FollowerScreen(navController: NavHostController, viewModel: HomeViewModel) {
    val argument = navController.currentBackStackEntry?.arguments

    val followers: MutableState<List<Follower>> = rememberSaveable {
        mutableStateOf(emptyList())
    }

    argument?.getString("userName")?.let {
        followers.value = viewModel.findFollowers(it) ?: emptyList()
    }

    LazyColumn {
        items(followers.value) {
            FollowerItems(follower = it)
        }
    }

}

@Composable
fun FollowerItems(follower: Follower) {
    Row {
        UserImage(imageUrl = follower.imageUrl, userName = follower.name)
        FollowerInfo(follower = follower)
    }
}

@Composable
fun FollowerInfo(follower: Follower) {
    Column {
        Text(text = follower.name)
        Text(text = follower.htmlUrl)
    }
}


