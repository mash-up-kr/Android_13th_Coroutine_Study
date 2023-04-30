sealed class ResultWrapper<out T, out R> {
    data class Success<out T>(val response: T) : ResultWrapper<T, Nothing>()
    data class Fail<out R>(val error: R) : ResultWrapper<Nothing, R>()
    object Noting : ResultWrapper<Nothing, Noting>()
}