package sample.base.app.utils.view

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import sample.base.app.base.BaseAdapterItemReyclerView
import sample.base.app.data.model.Article
import sample.base.app.ui.main.MainAdapter


object BindingAdapter {

    @BindingAdapter("app:viewState")
    @JvmStatic
    fun setViewState(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @BindingAdapter("adapter")
    @JvmStatic
    fun <V : Article> addCastItems(recyclerView: RecyclerView, casts: ArrayList<V>) {
        var adapter: BaseAdapterItemReyclerView<V,*>? = recyclerView.adapter as BaseAdapterItemReyclerView<V,*>?
        adapter?.addAll(casts)
    }

    @SuppressLint("PrivateResource")
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setImageUrl(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}