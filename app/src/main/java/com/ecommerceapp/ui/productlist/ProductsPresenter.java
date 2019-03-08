package com.ecommerceapp.ui.productlist;

import com.ecommerceapp.data.model.ProductModel;
import com.ecommerceapp.data.model.ProductModel.Product;
import com.ecommerceapp.data.source.remote.DataSource;
import com.ecommerceapp.data.source.remote.RemoteDataSource;
import timber.log.Timber;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductsPresenter implements IProductsContract.Presenter {

    private RemoteDataSource mRemoteDataSource;
    private IProductsContract.View mView;
    private IProductsContract.Navigation mNavigation;

    public ProductsPresenter(RemoteDataSource remoteDataSource, IProductsContract.View view, IProductsContract.Navigation navigation) {
        super();
        this.mRemoteDataSource = remoteDataSource;
        this.mView = view;
        this.mNavigation = navigation;
    }

    @Override
    public void getProducts() {

        mRemoteDataSource.getProductList(new DataSource.ResponseCallback<ProductModel>() {
            @Override
            public void onSuccess(ProductModel productModel) {

                if (productModel != null) {

                    List<Product> products = productModel.getData();
                    Timber.i("getProducts() : size=" + productModel.getData().size());
                    if (products != null & products.size() > 0) {
                        mView.showProducts(products);
                    } else {
                        mView.noDataAvailable();

                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Timber.i("getProducts() : Exception=" + throwable);
                mView.noDataAvailable();
                mView.showLongToast("" + throwable);

            }
        });

    }

    @Override
    public List<Product> getSortedProducts(List<Product> products, String sortBy, String order) {
        if (sortBy.equalsIgnoreCase("A")) {
            if (order.equalsIgnoreCase("asc")) {
                Collections.sort(products, new Comparator<Product>() {
                    @Override
                    public int compare(Product lhs, Product rhs) {

                        return Integer.valueOf(lhs.getSortProps().getA()).compareTo(rhs.getSortProps().getA());
                    }
                });

            }
            else if(order.equalsIgnoreCase("desc"))
            {
                Collections.sort(products, new Comparator<Product>() {
                    @Override
                    public int compare(Product lhs, Product rhs) {

                        return Integer.valueOf(rhs.getSortProps().getA()).compareTo(lhs.getSortProps().getA());
                    }
                });


            }

        } else if (sortBy.equalsIgnoreCase("B")) {

            if (order.equalsIgnoreCase("asc")) {
                Collections.sort(products, new Comparator<Product>() {
                    @Override
                    public int compare(Product lhs, Product rhs) {

                        return Integer.valueOf(lhs.getSortProps().getB()).compareTo(rhs.getSortProps().getB());
                    }
                });

            }
            else if(order.equalsIgnoreCase("desc"))
            {
                Collections.sort(products, new Comparator<Product>() {
                    @Override
                    public int compare(Product lhs, Product rhs) {

                        return Integer.valueOf(rhs.getSortProps().getB()).compareTo(lhs.getSortProps().getB());
                    }
                });


            }


        } else if (sortBy.equalsIgnoreCase("C")) {

            if (order.equalsIgnoreCase("asc")) {
                Collections.sort(products, new Comparator<Product>() {
                    @Override
                    public int compare(Product lhs, Product rhs) {

                        return Integer.valueOf(lhs.getSortProps().getC()).compareTo(rhs.getSortProps().getC());
                    }
                });

            }
            else if(order.equalsIgnoreCase("desc"))
            {
                Collections.sort(products, new Comparator<Product>() {
                    @Override
                    public int compare(Product lhs, Product rhs) {

                        return Integer.valueOf(rhs.getSortProps().getC()).compareTo(lhs.getSortProps().getC());
                    }
                });


            }


        }

        return products;
    }
}
