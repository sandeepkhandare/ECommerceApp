package com.ecommerceapp.ui.productlist;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.ecommerceapp.BR;
import com.ecommerceapp.R;
import com.ecommerceapp.data.model.ProductModel.Product;
import com.ecommerceapp.databinding.ItemProductBinding;
import com.ecommerceapp.ui.base.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductRVAdapter extends BaseRecyclerViewAdapter<Product, ProductRVAdapter.VHEvents> implements Filterable {


    private List<Product> mProducts;
    private List<Product> mProductsFiltered;
    private IProductRVItemClick mIProductRVItemClick;


    public ProductRVAdapter() {
        this.mProducts = new ArrayList<>();
    }

    public void setItemClick(IProductRVItemClick itemClick) {
        this.mIProductRVItemClick = itemClick;
    }

    @NonNull
    @Override
    public ProductRVAdapter.VHEvents onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemProductBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_product, parent, false);
        return new ProductRVAdapter.VHEvents(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRVAdapter.VHEvents holder, int position) {

        Product product = mProductsFiltered.get(position);
        holder.bind(product);
        holder.viewDataBinding.getRoot().setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return mProductsFiltered.size();
    }

    @Override
    public void clearItems() {
        this.mProducts.clear();
    }

    @Override
    public void addItems(List<Product> items) {
        this.mProducts.addAll(items);
        this.mProductsFiltered = new ArrayList<>();
        this.mProductsFiltered = mProducts;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String filter = charSequence.toString();
                if (TextUtils.isEmpty(filter)) {
                    mProductsFiltered = mProducts;

                } else {
                    List<Product> tempList = new ArrayList<>();
                    for (Product product : mProducts) {
                        if (product.getName().toLowerCase().startsWith(filter.toLowerCase()) || product.getCategoryName().toLowerCase().contains(filter.toLowerCase()) || product.getDescription().toLowerCase().contains(filter.toLowerCase())) {
                            tempList.add(product);
                        }
                    }
                    mProductsFiltered = tempList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mProductsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                mProductsFiltered = (List<Product>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    public class VHEvents extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ItemProductBinding viewDataBinding;

        public VHEvents(ItemProductBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
        }

        public void bind(Object obj) {
            viewDataBinding.setVariable(BR.obj, obj);
            viewDataBinding.executePendingBindings();

        }

        @Override
        public void onClick(View view) {
            if (mIProductRVItemClick != null) {
                mIProductRVItemClick.onRecyclerItemClick(view, getAdapterPosition(), mProducts.get(getAdapterPosition()));
            }
        }
    }


}
