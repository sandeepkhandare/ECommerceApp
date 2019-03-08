package com.ecommerceapp.ui.productlist;

import com.ecommerceapp.data.model.ProductModel;
import com.ecommerceapp.ui.base.IBaseView;

import java.util.List;

public interface IProductsContract {


    interface View extends IBaseView {
        void showProducts(List<ProductModel.Product> productList);
        void noDataAvailable();
        void init();

    }

    interface Presenter {
        void getProducts();
        List<ProductModel.Product> getSortedProducts(List<ProductModel.Product> products,String sortBy,String order);

    }

    interface Navigation {
        void goToProductDetailActivity(String id,String name);
    }
}
