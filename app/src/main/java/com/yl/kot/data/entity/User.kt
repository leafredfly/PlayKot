package com.yl.kot.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Author: lshun
 * Date: 2019/07/27
 * Desc:
 */
@Parcelize
data class User(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("username") var username: String?,
    @SerializedName("password") var password: String?,
    @SerializedName("nickname") var nickname: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("icon") var icon: String?,
    @SerializedName("type") var type: Int = 0,
    @SerializedName("admin") var admin: Boolean = false,
    @SerializedName("token") var token: String?,
    @SerializedName("chapterTops") var chapterTops: Array<String>?,
    @SerializedName("collectIds") var collectIds: IntArray?
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as User
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}