package com.yl.kot.data.remote

import java.io.IOException

/**
 * Author: Want-Sleep
 * Date: 2018/08/14
 * Desc:l
 */
class ApiException constructor(val errorCode: Int, val errorMsg: String) : IOException(errorMsg) {
    companion object {
        const val CODE_SUCCESS = 0
    }
}
