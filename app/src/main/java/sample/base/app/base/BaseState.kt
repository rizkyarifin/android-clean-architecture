package sample.base.app.base

sealed class BaseState {
    object Error : BaseState()
    object Loading : BaseState()
    data class Data<T> (val data : T) : BaseState()
}