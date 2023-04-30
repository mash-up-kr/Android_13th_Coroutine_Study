sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Failure(
        val exception: Exception? = Exception("Unknown Error"),
        val message: String = exception?.localizedMessage ?: ""
    ) : NetworkResult<Nothing>()

    object Error : NetworkResult<Nothing>()

}
