package sample.base.app.base

import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BaseViewModel : ViewModel(){

    val mCompositeDisposable = CompositeDisposable()

    fun launch(job:() -> Disposable) {
        mCompositeDisposable.add(job())
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }
}