package sample.base.app.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_footer.view.*
import sample.base.app.R
import sample.base.app.utils.view.State

class ListFooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(status: State?) {
        itemView.progress_bar.visibility = if (status == State.LOADING) VISIBLE else View.INVISIBLE
        itemView.txt_error.visibility = if (status == State.ERROR) VISIBLE else View.INVISIBLE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_footer, parent, false)
            view.txt_error.setOnClickListener { retry() }
            return ListFooterViewHolder(view)
        }
    }
}