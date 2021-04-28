package com.app.productapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {

//    @SuppressLint("SetTextI18n")
//    @BindingAdapter("bind:productDiscount")
//    @JvmStatic
//    fun discount(textView: TextView, productWithCoupon: ProductWithCoupon) {
//        if (productWithCoupon.amount > 0) {
//            if (productWithCoupon.type.equals(AppConstant.DiscountTYpe.AMOUNT)) {
//                textView.text = "Discount: " + textView.context.getString(
//                    R.string.rupee,
//                    productWithCoupon.amount.toString()
//                )
//            } else {
//                textView.text = "Discount: " + textView.context.getString(
//                    R.string.percentage_x,
//                    productWithCoupon.amount.toInt()
//                )
//            }
//        } else {
//            textView.text = "Discount: NA"
//        }
//    }

    @BindingAdapter("bind:image")
    @JvmStatic
    fun setImage(imageView: ImageView, url: String?) {
//        if (!url.isNullOrEmpty()) {
//            Glide.with(imageView.context)
//                .load(url)
//                .into(imageView)
//        }
    }
}