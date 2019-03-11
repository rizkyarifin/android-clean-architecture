package sample.base.app.utils.ext

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.HttpException
import sample.base.app.data.model.HttpCallFailureException
import sample.base.app.data.model.NoNetworkException
import sample.base.app.data.model.ServerUnreachableException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


fun <T> Single<T>.mapNetworkErrors(): Single<T> =
    this.onErrorResumeNext { error ->
        when (error) {
            is SocketTimeoutException -> Single.error(NoNetworkException(error))
            is UnknownHostException -> Single.error(ServerUnreachableException(error))
            is HttpException -> Single.error(HttpCallFailureException(error))
            else -> Single.error(error)
        }
    }