package com.ecommerceapp.ui.productlist;

import android.view.View;
import com.ecommerceapp.data.model.ProductModel.Product;

public interface IProductRVItemClick {
    void onRecyclerItemClick(View view, int position, Product product);

}
