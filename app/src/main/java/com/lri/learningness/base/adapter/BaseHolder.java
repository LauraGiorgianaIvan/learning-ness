package com.lri.learningness.base.adapter;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {
    public BaseHolder(@NonNull View itemView) {
        super(itemView);
        bindView(itemView);
    }

    protected abstract void bindView(View view);

    protected abstract void bindItem(T item);
}
