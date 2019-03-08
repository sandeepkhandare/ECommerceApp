package com.ecommerceapp.ui.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ecommerceapp.R;
import com.ecommerceapp.data.model.ProductModel;
import com.ecommerceapp.data.model.ProductModel.Product;
import com.ecommerceapp.data.source.remote.RemoteDataSource;
import com.ecommerceapp.databinding.ActivityProductsBinding;
import com.ecommerceapp.ui.base.BaseActivity;
import com.ecommerceapp.ui.productdetail.ProductDetailActivity;
import timber.log.Timber;

import java.util.List;

public class ProductsActivity extends BaseActivity implements IProductsContract.View, IProductsContract.Navigation, IProductRVItemClick {


    private IProductsContract.Presenter mPresenter;
    private ActivityProductsBinding mActivityProductsBinding;
    List<Product> mProducts;
    ProductRVAdapter mProductRVAdapter;
    boolean flagA = true, flagB = true, flagC = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityProductsBinding = setContentLayout(R.layout.activity_products);
        init();
        mPresenter.getProducts();
    }

    @Override
    public void showProducts(List<ProductModel.Product> productList) {

        mActivityProductsBinding.rvProducts.setVisibility(View.VISIBLE);
        mActivityProductsBinding.tvNoData.setVisibility(View.GONE);

        mProducts = productList;
        mProductRVAdapter.addItems(mProducts);

    }

    @Override
    public void noDataAvailable() {

        mActivityProductsBinding.rvProducts.setVisibility(View.GONE);
        mActivityProductsBinding.tvNoData.setVisibility(View.VISIBLE);
    }


    @Override
    public void init() {
        updateTitle("Products");


        mPresenter = new ProductsPresenter(new RemoteDataSource(), this, this);
        createAdapter();

        mActivityBaseBinding.lyHeader.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivityBaseBinding.lyHeader.imgSearch.setVisibility(View.GONE);
                mActivityBaseBinding.lyHeader.edtSearch.setVisibility(View.VISIBLE);
                mActivityBaseBinding.lyHeader.imgClear.setVisibility(View.VISIBLE);


            }
        });

        mActivityBaseBinding.lyHeader.imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivityBaseBinding.lyHeader.imgSearch.setVisibility(View.VISIBLE);
                mActivityBaseBinding.lyHeader.edtSearch.setVisibility(View.GONE);
                mActivityBaseBinding.lyHeader.imgClear.setVisibility(View.GONE);

                mActivityBaseBinding.lyHeader.edtSearch.setText("");
                mProductRVAdapter.getFilter().filter("");


            }
        });

        mActivityBaseBinding.lyHeader.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                mProductRVAdapter.getFilter().filter(editable.toString());

            }
        });
        mActivityProductsBinding.viewSortByA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mProducts != null) {
                    if (flagA) {
                        flagA = false;

                        List<Product> products =
                                mPresenter.getSortedProducts(mProducts, "A", "asc");
                        mProductRVAdapter.clearItems();
                        mProductRVAdapter.addItems(products);

                    } else {
                        flagA = true;
                        List<Product> products =
                                mPresenter.getSortedProducts(mProducts, "A", "desc");
                        mProductRVAdapter.clearItems();
                        mProductRVAdapter.addItems(products);


                    }
                }


            }
        });
        mActivityProductsBinding.viewSortByB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mProducts != null) {
                    if (flagB) {
                        flagB = false;

                        List<Product> products =
                                mPresenter.getSortedProducts(mProducts, "B", "asc");
                        mProductRVAdapter.clearItems();
                        mProductRVAdapter.addItems(products);

                    } else {
                        flagB = true;
                        List<Product> products =
                                mPresenter.getSortedProducts(mProducts, "B", "desc");
                        mProductRVAdapter.clearItems();
                        mProductRVAdapter.addItems(products);


                    }
                }


            }
        });
        mActivityProductsBinding.viewSortByC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mProducts != null) {
                    if (flagC) {
                        flagC = false;

                        List<Product> products =
                                mPresenter.getSortedProducts(mProducts, "C", "asc");
                        mProductRVAdapter.clearItems();
                        mProductRVAdapter.addItems(products);

                    } else {
                        flagC = true;
                        List<Product> products =
                                mPresenter.getSortedProducts(mProducts, "C", "desc");
                        mProductRVAdapter.clearItems();
                        mProductRVAdapter.addItems(products);

                    }

                }


            }
        });
    }

    private void createAdapter() {
        mProductRVAdapter = new ProductRVAdapter();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mActivityProductsBinding.rvProducts.setLayoutManager(layoutManager);
        mActivityProductsBinding.rvProducts.setAdapter(mProductRVAdapter);
        mProductRVAdapter.setItemClick(this);
    }

    @Override
    public void goToProductDetailActivity(String id,String name) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        startActivity(intent);


    }

    @Override
    public void onRecyclerItemClick(View view, int position, Product product) {
        Timber.i("position=" + position);

        if (product != null) {

            String id = product.getId();
            String name=product.getName();
            goToProductDetailActivity(id,name);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
