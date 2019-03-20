package sample.base.app.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


open class BaseViewModel : ViewModel(){

    val isLoading = ObservableBoolean(false)
    val mCompositeDisposable = CompositeDisposable()

    fun launch(job:() -> Disposable) {
        mCompositeDisposable.add(job())
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }

    fun handleError(throwable: Throwable) : String {
        if (throwable is UnknownHostException || throwable is IOException ||
            throwable is SocketTimeoutException){
            return "Internet is busy trs again later."
        }else if (throwable is HttpException){
            val code = throwable.code()
            if (code == 400){

                return "Wrong"
            }else {
                return "Internet is busy trs again later."
            }
        }else {
            return "Something went wrong"
        }
    }
}