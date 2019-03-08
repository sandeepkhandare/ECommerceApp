package com.ecommerceapp.data.source.remote;

import com.ecommerceapp.data.model.ProductModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource implements DataSource {


    @Override
    public void getProductList(final ResponseCallback<ProductModel> callback) {

        RetrofitClient.getNetworkClass().getProductList().enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {

                if (response.isSuccessful()) {

                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new RuntimeException(response.message()));
                }
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }

    @Override
    public void getProductDetail(String id, ResponseCallback<ProductModel.Product> callback)
    {

        RetrofitClient.getNetworkClass().getProductDetail(id).enqueue(new Callback<ProductModel.Product>() {
            @Override
            public void onResponse(Call<ProductModel.Product> call, Response<ProductModel.Product> response) {

                if (response.isSuccessful()) {

                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new RuntimeException(response.message()));
                }
            }

            @Override
            public void onFailure(Call<ProductModel.Product> call, Throwable t) {

                callback.onFailure(t);
            }
        });
    }
}
