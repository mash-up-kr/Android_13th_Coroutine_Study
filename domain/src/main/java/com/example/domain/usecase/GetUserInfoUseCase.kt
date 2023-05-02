package com.example.domain.usecase

import com.example.domain.repository.UserRepository
import javax.inject.Inject

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/24
 */
class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(userName: String) = userRepository.getUserInfo(userName)
}