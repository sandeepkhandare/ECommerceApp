package com.ecommerceapp.data.source.remote;

import com.ecommerceapp.data.model.ProductModel;
import com.ecommerceapp.data.model.ProductModel.Product;

public interface DataSource {
    interface ResponseCallback<T> {
        void onSuccess(T t);
        void onFailure(Throwable throwable);
    }

    void getProductList(ResponseCallback<ProductModel> callback);
    void getProductDetail(String id,ResponseCallback<Product> callback);

}
