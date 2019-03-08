package com.ecommerceapp.ui.base;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<U, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>
{
    public abstract void clearItems();
    public abstract void addItems(List<U> items);
}
