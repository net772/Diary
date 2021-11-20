package com.example.a2alertdialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private lateinit var mContext: Context
    private lateinit var mListener: DialogListener
    private lateinit var mNumListener: GetNumListener
    private var changePasswordMode = false

    fun init(applicationContext: Context, listener: DialogListener, numListener: GetNumListener){
        mContext = applicationContext
        mListener = listener
        mNumListener = numListener
    }

    val onClickListener = View.OnClickListener { view ->
        when(view.id) {
            R.id.openButton -> openButton()
            R.id.changePasswordBtn -> changePasswordBtn()
        }
    }

    private fun changePasswordBtn() {
        if(changePasswordMode) {
            // 번호 저장
            val passwordFromUser = mNumListener.getNum()
            PreferenceHelper.setPreference(mContext, "password", passwordFromUser)
            Toast.makeText(mContext, "변경이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            changePasswordMode = false

        } else {
            val passwordFromUser = mNumListener.getNum()
            if(PreferenceHelper.getString(mContext,"password").equals(passwordFromUser)) {
                changePasswordMode = true
                Toast.makeText(mContext, "변경할 패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                showErrorDialog()
            }
        }
    }

    private fun showErrorDialog() {
        mListener.getActivity { activity ->
            AlertDialog.Builder(activity)
                .setTitle("실패!!")
                .setMessage("비밀번호가 잘못되었습니다!")
                .setPositiveButton("확인") { _, _ ->}
                .create()
                .show()
        }

    }


    private fun openButton() {
        if (changePasswordMode) {
            Toast.makeText(mContext, "비밀번호 변경 중입니다.", Toast.LENGTH_SHORT).show()
            return
        }
        val passwordFromUser = mNumListener.getNum()
        if(PreferenceHelper.getString(mContext,"password").equals(passwordFromUser)) {
            Toast.makeText(mContext, "로그인.", Toast.LENGTH_SHORT).show()
            mListener.getActivity { activity ->
                activity.startActivity(Intent(mContext, DiaryActivity::class.java))
            }
        } else {
            showErrorDialog()
    }
    }

    interface DialogListener {
        fun getActivity(callback: (Activity) -> Unit)
    }

    interface GetNumListener {
        fun getNum() : String
    }
}