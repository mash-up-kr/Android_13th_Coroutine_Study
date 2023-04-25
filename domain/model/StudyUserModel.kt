package model

data class SearchModel(
    val searchList: List<User> = emptyList()
) {
    data class User(
        val name: String = ""
        val avatarUrl: String = "",
        val follwerCount: Int = "",
        val follwers: List<Follwer> = emptyList()
    ) {
        data class Follwer(
            val name: String = "",
            val avatarUrl: String = "",
            val userPageUrl: String = ""
        )
    }
}

