package com.ecommerceapp.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ecommerceapp.R;

public class Databinder {

    @BindingAdapter(value = {"app:product_pic", "app:product_pic_error"}, requireAll = false)
    public static void setProductImage(ImageView view, String url, Drawable error) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_photo_library_black_24dp)
                .error(R.drawable.ic_photo_library_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(view.getContext()).load(url)
                .apply(options)
                .into(view);
    }

}
