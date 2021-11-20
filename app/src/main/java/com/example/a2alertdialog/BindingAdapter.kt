package com.example.a2alertdialog

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @BindingAdapter("onClickListener")
    @JvmStatic
    fun setOnclickListener(view: View, listener: View.OnClickListener?) {
        view.setOnClickListener(listener)
    }
}