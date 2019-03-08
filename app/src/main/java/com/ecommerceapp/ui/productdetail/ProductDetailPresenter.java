package com.ecommerceapp.ui.productdetail;

import com.ecommerceapp.data.model.ProductModel;
import com.ecommerceapp.data.source.remote.DataSource;
import com.ecommerceapp.data.source.remote.RemoteDataSource;

public class ProductDetailPresenter implements IProductDetailContract.Presenter {

    private RemoteDataSource mRemoteDataSource;
    private IProductDetailContract.View mView;
    private IProductDetailContract.Navigation mNavigation;

    public ProductDetailPresenter(RemoteDataSource remoteDataSource, IProductDetailContract.View view, IProductDetailContract.Navigation navigation) {
        super();
        this.mRemoteDataSource = remoteDataSource;
        this.mView = view;
        this.mNavigation = navigation;
    }

    @Override
    public void getProductDetail(String id) {

        mRemoteDataSource.getProductDetail(id, new DataSource.ResponseCallback<ProductModel.Product>() {
            @Override
            public void onSuccess(ProductModel.Product product) {


                if (product != null) {
                    mView.showProductDetail(product);
                } else {
                    mView.noDataAvailable();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

                mView.noDataAvailable();
                mView.showLongToast("" + throwable);
            }
        });

    }
}
