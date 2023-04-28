package model

data class User(
    val login : String = "",
    val avatarUrl : String = "",
    val url : String = "",
    val followerCount : Int = 0,
    val followers : List<User> = emptyList(),
)
