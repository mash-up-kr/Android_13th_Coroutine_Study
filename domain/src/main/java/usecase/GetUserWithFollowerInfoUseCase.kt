package usecase

import kotlinx.coroutines.flow.Flow
import model.UserInfoResponse
import repository.UserWithFollowerInfoRepository
import javax.inject.Inject

class GetUserWithFollowerInfoUseCase @Inject constructor(
    private val userWithFollowerInfoRepository: UserWithFollowerInfoRepository
) {
    suspend operator fun invoke(query: String): Flow<List<UserInfoResponse>> {
        return userWithFollowerInfoRepository.getUserWithFollowerInfo(query)
    }
}