package sample.base.app.base

sealed class BaseState{
    data class  Error<out T> (val error : T) : BaseState()
    object Loading : BaseState()
    data class Data<out T> (val data : T) : BaseState()
}

data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val message: String? = null
)

sealed class ResourceState {
    object LOADING : ResourceState()
    object SUCCESS : ResourceState()
    object ERROR : ResourceState()
}