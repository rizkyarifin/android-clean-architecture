package sample.base.app.base

sealed class BaseState{
    data class  Error<T> (val error : T) : BaseState()
    object Loading : BaseState()
    data class Data<T> (val data : T) : BaseState()
}