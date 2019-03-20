package sample.base.app.utils.view

import android.databinding.BindingAdapter
import android.view.View


@BindingAdapter("app:goneUnless")
fun viewState(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}