package com.moong.ktdemo.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat

/**
 * @author DiaoMengq dmq1212@qq.com
 * @date created on 2022/6/25
 */
private val MAIN_PERMISSIONS = arrayOf(
    Manifest.permission.INTERNET,
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.READ_EXTERNAL_STORAGE
)

private const val PERMISSION_CODE = 5980

fun requestPermission(activity: Activity) {
    // 当API大于 23 时，才动态申请权限
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        ActivityCompat.requestPermissions(activity, MAIN_PERMISSIONS, PERMISSION_CODE);
    }
}


class AppPermissionManager(context: Context) {
    private val mContext = context

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE ->                 //权限请求失败
                if (grantResults.size == MAIN_PERMISSIONS.size) {
                    for (result in grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            //弹出对话框引导用户去设置
                            showRequestPermissionDialog()
                            // Toast.makeText(mContext, "请求权限被拒绝", Toast.LENGTH_LONG).show()
                            showToast(mContext, "请求权限被拒绝")
                            break
                        }
                    }
                } else {
                    showToast(mContext, "已授权")
                }
        }
    }

    private fun showRequestPermissionDialog() {
        AlertDialog.Builder(mContext)
            .setMessage("应用需要网络和读写储存空间权限，是否去设置？")
            .setPositiveButton("是") { dialog, _ ->
                dialog.dismiss()
                goToAppSetting()
            }
            .setNegativeButton(
                "否"
            ) { dialog, which -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }

    // 跳转到当前应用的设置界面
    private fun goToAppSetting() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri: Uri = Uri.fromParts("package", mContext.packageName, null)
        intent.data = uri
        mContext.startActivity(intent)
    }
}