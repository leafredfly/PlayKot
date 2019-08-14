package com.yl.kot

import android.app.Activity
import android.content.Intent

/**
 * Author: Want-Sleep
 * Date: 2019/08/14
 * Desc:
 */

/**
 * Activity.java extension start
 *
 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 */

fun Activity.startActivity(cls: Class<*>) {
    val intent = Intent(this, cls)
    startActivity(intent)
}

fun Activity.startActivityForResult(cls: Class<*>, requestCode: Int) {
    val intent = Intent(this, cls)
    startActivityForResult(intent, requestCode)
}
/**
 * Activity.java extension end
 *
 * <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
 */