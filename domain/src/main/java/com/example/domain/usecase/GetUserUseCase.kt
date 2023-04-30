package com.example.domain.usecase

import com.example.domain.model.UserDomainModel
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(name: String): Flow<UserDomainModel> {
        return userRepository.getUserInfo(name)
    }
}
