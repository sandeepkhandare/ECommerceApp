package com.ecommerceapp.data.source.remote;

import com.ecommerceapp.data.model.ProductModel;
import retrofit2.Call;
import retrofit2.http.GET;
import com.ecommerceapp.data.model.ProductModel.Product;
import retrofit2.http.Path;

public interface NetworkApi {

    @GET("productList")
    Call<ProductModel> getProductList();
    @GET("productDetail/{id}/")
    Call<Product> getProductDetail(@Path("id") String user);
}
