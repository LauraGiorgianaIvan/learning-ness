package com.lri.learningness.base.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAppAdapter<T> extends RecyclerView.Adapter<BaseHolder<T>> {

    private List<T> items;

    public void updateData(List<T> data) {
        items = data;
        notifyDataSetChanged();
    }

    public List<T> getItems() {
        return this.items;
    }

    @NonNull
    @Override
    public BaseHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewHolder(LayoutInflater.from(parent.getContext()), parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder<T> holder, int position) {
        holder.bindItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    protected abstract BaseHolder<T> viewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);
}
