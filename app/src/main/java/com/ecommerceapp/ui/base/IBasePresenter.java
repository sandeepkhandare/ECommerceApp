package com.ecommerceapp.ui.base;

public interface IBasePresenter<ViewType, NaviType> {

    void onCreate();

    void onStart();

    void onPaused();

    void onResume();

    void onStop();

    void onBackPressed();

    ViewType view();

    NaviType navigator();
}
