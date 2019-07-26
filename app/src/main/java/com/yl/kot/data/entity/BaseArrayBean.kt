package com.yl.kot.data.entity

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */
data class BaseArrayBean(
    @SerializedName("errorCode") val code: Int = 0,
    @SerializedName("errorMsg") val message: String,
    @SerializedName("data") val data: JsonArray
)
