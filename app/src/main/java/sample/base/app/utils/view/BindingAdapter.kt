package sample.base.app.utils.view

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ProgressBar


object BindingAdapter {

    @BindingAdapter("viewState")
    @JvmStatic
    fun setViewState(view: ProgressBar, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}