package model

data class StudyUserModel(
    val id: Int
    val follwers: List<Follwer> = emptyList()
) {
    data class Follwer(
        val name: String = "",
        val avatarUrl: String = "",
        val userPageUrl: String = ""
    )
}

