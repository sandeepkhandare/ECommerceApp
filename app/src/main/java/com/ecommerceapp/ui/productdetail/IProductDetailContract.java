package com.ecommerceapp.ui.productdetail;

import com.ecommerceapp.data.model.ProductModel.Product;
import com.ecommerceapp.ui.base.IBaseView;

import java.util.List;

public interface IProductDetailContract {

    interface View extends IBaseView {
        void showProductDetail(Product product);
        void noDataAvailable();
        void init();

    }

    interface Presenter {
        void getProductDetail(String id);
    }

    interface Navigation {
    }
}
