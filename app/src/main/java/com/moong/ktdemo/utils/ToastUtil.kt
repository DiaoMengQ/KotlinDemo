package com.moong.ktdemo.utils

import android.content.Context
import android.widget.Toast

private var myToast: Toast? = null

fun toast(context: Context, text: String) {
    if (myToast != null) {
        myToast?.cancel()
    }
    myToast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
    myToast?.show()
}

fun cancelToast() {
    myToast?.cancel()
}

/**
 * 封装的 toast 类，使toast弹出时自动取消上一个toast
 * @author Demi dmq1212@qq.com
 * @date created on 2022/6/12
 */
class ToastUtil