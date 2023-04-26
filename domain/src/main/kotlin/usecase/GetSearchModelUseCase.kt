package usecase

import repository.GitHubRepository
import javax.inject.Inject

class GetSearchModelUseCase @Inject constructor(
    private val repository: GitHubRepository
) {
    operator fun invoke(query: String = "KimHance") = repository.getSearchModel(query)
}