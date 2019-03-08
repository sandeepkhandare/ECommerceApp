package com.ecommerceapp.ui.base;

public interface IBaseView {

    void showLoader();

    void dismissLoader();

    void showMessageDialog(String message, String positiveButton,
                           DialogDismiss positiveListener,
                           String negativeButton,
                           DialogDismiss negativeListener);

    void dismissMessageDialog();

    void showErrorDialog(String message, DialogDismiss dismissListener);

    void dismissErrorDialog();

    void showToast(String message);

    void showLongToast(String message);

    void showLongToast(String message, int GRAVITY);

    void showShortToast(String message);

    interface DialogDismiss {

        void onDismiss();
    }

    void updateTitle(String title) ;
}
