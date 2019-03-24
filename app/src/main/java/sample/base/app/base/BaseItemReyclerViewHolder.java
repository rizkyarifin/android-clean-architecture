package sample.base.app.base;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Okta Dwi Priatna on 5/16/17.
 * okta.dwi1@gmail.com
 */

public abstract class BaseItemReyclerViewHolder<Data> extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {

    protected Context mContext;

    private BaseAdapterItemReyclerView.OnItemClickListener itemClickListener;
    private BaseAdapterItemReyclerView.OnLongItemClickListener longItemClickListener;

    private boolean hasHeader = false;

    public BaseItemReyclerViewHolder(Context mContext, View itemView,
                                      BaseAdapterItemReyclerView.OnItemClickListener itemClickListener,
                                      BaseAdapterItemReyclerView.OnLongItemClickListener longItemClickListener) {
        super(itemView);
//        ButterKnife.bind(this, itemView);
        this.mContext = mContext;
        this.itemClickListener = itemClickListener;
        this.longItemClickListener = longItemClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public BaseItemReyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(Data data);

    public boolean isHasHeader() {
        return hasHeader;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    @Override
    public void onClick(View v) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(v, hasHeader ? getAdapterPosition() - 1 : getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (longItemClickListener != null) {
            longItemClickListener.onLongItemClick(v,
                    hasHeader ? getAdapterPosition() - 1 : getAdapterPosition());
            return true;
        }
        return false;
    }
}

