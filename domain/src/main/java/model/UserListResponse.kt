package model

/**
 * @Created by 김현국 2023/04/28
 */
data class UserListResponse(

    val items: List<User>,
    val total_count: Int,
)
