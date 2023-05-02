package com.example.domain.usecase

import com.example.domain.repository.UserRepository
import javax.inject.Inject

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/27
 */
class SearchUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(query: String) = userRepository.searchUser(query)
}