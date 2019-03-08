package com.ecommerceapp.ui.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ecommerceapp.R;
import com.ecommerceapp.data.source.remote.RemoteDataSource;
import com.ecommerceapp.databinding.ActivityBaseBinding;

public class BaseActivity extends AppCompatActivity implements IBaseView {

    public static final int READ_WRITE_STORAGE = 52;
    RemoteDataSource dataSource;
    public ActivityBaseBinding mActivityBaseBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        mActivityBaseBinding.lyHeader.imgBack.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp));
        dataSource = new RemoteDataSource();

    }


    protected <T extends ViewDataBinding> T setContentLayout(int layout) {
        return DataBindingUtil.inflate(getLayoutInflater(), layout, mActivityBaseBinding.cly, true);
    }

    public View getRootView() {
        return mActivityBaseBinding.cly;
    }

    public void setTitleText(String titleText) {
        mActivityBaseBinding.lyHeader.tvTitle.setText(titleText);
    }

    public void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {

            InputMethodManager imm = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            }
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    private Dialog messageDialog = null;
    private ProgressDialog progress = null;

    private boolean isShowingLoader = false;
    private Dialog loadingDialog = null;


    @Override
    public void showLoader() {

        try {
            if (isShowingLoader) {
                return;
            }

            dismissLoader();

            loadingDialog = new Dialog(this);
            if (loadingDialog != null) {
                loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                loadingDialog.setCancelable(false);
                loadingDialog.setContentView(R.layout.dialog_connecting);
                loadingDialog.show();
                TextView message = (TextView) loadingDialog.findViewById(R.id.message);
                message.setText("Loading..");
                isShowingLoader = true;
            }
        } catch (Exception e) {

        }

    }

    @Override
    public void dismissLoader() {

        try {
            if (!isShowingLoader) return;

            isShowingLoader = false;

            if (loadingDialog != null) {
                loadingDialog.dismiss();
            }
        } catch (Exception e) {
        }
    }


    @Override
    public void showMessageDialog(String message, String positiveButton, DialogDismiss positiveListener, String negativeButton, DialogDismiss negativeListener) {
        try {
            {
                dismissMessageDialog();

                messageDialog = new Dialog(BaseActivity.this, R.style.ZoomInOutDialog);
                messageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                messageDialog.setContentView(R.layout.dialog_alert);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = messageDialog.getWindow();
                lp.copyFrom(window.getAttributes());
                //This makes the dialog take up the full width
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);
                messageDialog.setCancelable(false);
                messageDialog.show();

                TextView messagetxt = (TextView) messageDialog.findViewById(R.id.message);
                TextView btnConfirm = (TextView) messageDialog.findViewById(R.id.confirm);
                TextView btnCancel = (TextView) messageDialog.findViewById(R.id.cancel);
                messagetxt.setText(message);


                if (!positiveButton.isEmpty()) {
                    btnConfirm.setText(positiveButton);

                } else {
                    btnConfirm.setVisibility(View.GONE);
                }

                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        positiveListener.onDismiss();
                        dismissMessageDialog();
                    }
                });

                if (negativeButton != null && !negativeButton.isEmpty()) {
                    btnCancel.setText(negativeButton);

                } else {
                    btnCancel.setVisibility(View.GONE);
                }

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        negativeListener.onDismiss();
                        dismissMessageDialog();
                    }
                });

            }
        } catch (Exception e) {

        }
    }

    @Override
    public void dismissMessageDialog() {
        if (messageDialog != null) {
            messageDialog.dismiss();

        }
    }

    //
    @Override
    public void showErrorDialog(String message, DialogDismiss dismissListener) {

        dismissErrorDialog();
        showMessageDialog(message, "OK", dismissListener, null, null);
    }

    @Override
    public void dismissErrorDialog() {
        dismissMessageDialog();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLongToast(String message, int GRAVITY) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void showShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void updateTitle(String title) {
        mActivityBaseBinding.lyHeader.tvTitle.setText(title);
    }


    public boolean requestPermission(String permission) {
        boolean isGranted = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
        if (!isGranted) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{permission},
                    READ_WRITE_STORAGE);
        }
        return isGranted;
    }

    public void isPermissionGranted(boolean isGranted, String permission) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case READ_WRITE_STORAGE:
                isPermissionGranted(grantResults[0] == PackageManager.PERMISSION_GRANTED, permissions[0]);
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    public ActivityBaseBinding baseBinder() {
        return mActivityBaseBinding;
    }


}
