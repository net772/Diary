package com.example.a2alertdialog

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.a2alertdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainViewModel.DialogListener, MainViewModel.GetNumListener {
    lateinit var mBinding : ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.vm = viewModel
        viewModel.init(applicationContext,this, this)
        initView()
    }

    private fun initView() {
        mBinding.numberPicker1.apply {
            minValue = 0
            maxValue = 9
        }

        mBinding.numberPicker2.apply {
            minValue = 0
            maxValue = 9
        }

        mBinding.numberPicker3.apply {
            minValue = 0
            maxValue = 9
        }
    }

    override fun getActivity(callback: (Activity) -> Unit) = callback(this)
    override fun getNum(): String = "${mBinding.numberPicker1.value}${mBinding.numberPicker2.value}${mBinding.numberPicker3.value}"

}

