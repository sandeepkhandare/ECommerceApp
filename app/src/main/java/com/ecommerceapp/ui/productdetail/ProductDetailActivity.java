package com.ecommerceapp.ui.productdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.ecommerceapp.R;
import com.ecommerceapp.data.model.ProductModel.Product;
import com.ecommerceapp.data.source.remote.RemoteDataSource;
import com.ecommerceapp.databinding.ActivityProductDetailBinding;
import com.ecommerceapp.ui.base.BaseActivity;
import timber.log.Timber;

public class ProductDetailActivity extends BaseActivity implements IProductDetailContract.View, IProductDetailContract.Navigation {


    private ActivityProductDetailBinding mActivityProductDetailBinding;
    private IProductDetailContract.Presenter mPresenter;
    private String id = "", name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityProductDetailBinding = setContentLayout(R.layout.activity_product_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            id = bundle.getString("id");
            name = bundle.getString("name");
            init();
            mPresenter.getProductDetail(id);
        }

    }

    @Override
    public void showProductDetail(Product product) {

        Timber.i("Product Name : " + product.getName());
        mActivityProductDetailBinding.setObj(product);
        mActivityProductDetailBinding.executePendingBindings();
    }

    @Override
    public void noDataAvailable() {

        mActivityProductDetailBinding.tvProductName.setVisibility(View.GONE);
        mActivityProductDetailBinding.tvProductCat.setVisibility(View.GONE);
        mActivityProductDetailBinding.tvProductPrice.setVisibility(View.GONE);
        mActivityProductDetailBinding.tvSize.setVisibility(View.GONE);
        mActivityProductDetailBinding.imgSize.setVisibility(View.GONE);
        mActivityProductDetailBinding.viewHorizontalLine1.setVisibility(View.GONE);

    }

    @Override
    public void init() {
        updateTitle("Product Detail");
        mPresenter = new ProductDetailPresenter(new RemoteDataSource(), this, this);
        mActivityBaseBinding.lyHeader.edtSearch.setVisibility(View.GONE);
        mActivityBaseBinding.lyHeader.imgSearch.setVisibility(View.GONE);
        mActivityBaseBinding.lyHeader.imgClear.setVisibility(View.GONE);
        mActivityBaseBinding.lyHeader.imgBack.setVisibility(View.VISIBLE);

        mActivityBaseBinding.lyHeader.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
