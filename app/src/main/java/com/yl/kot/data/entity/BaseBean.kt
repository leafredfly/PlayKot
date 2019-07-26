package com.yl.kot.data.entity

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */

data class BaseBean(
    @SerializedName("errorCode") val code: Int = 0,
    @SerializedName("errorMsg") val message: String,
    @SerializedName("data") val data: JsonObject
)