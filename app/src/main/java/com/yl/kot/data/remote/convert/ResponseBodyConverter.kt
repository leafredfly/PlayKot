package com.yl.kot.data.remote.convert

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.yl.kot.data.entity.BaseArrayBean
import com.yl.kot.data.entity.BaseBean
import com.yl.kot.data.remote.ApiException
import okhttp3.ResponseBody
import retrofit2.Converter
import java.lang.reflect.Type

/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */
internal class ResponseBodyConverter<T>(private val gson: Gson, private val type: Type) : Converter<ResponseBody, T> {

    override fun convert(value: ResponseBody): T {
        val response = value.string()
        return try {
            val baseBean = gson.fromJson(response, BaseBean::class.java)
            if (baseBean.code != 0) {
                throw ApiException(baseBean.code, baseBean.message)
            }
            gson.fromJson(baseBean.data, type)
        } catch (e: JsonSyntaxException) {
            val baseArrayBean = gson.fromJson(response, BaseArrayBean::class.java)
            if (baseArrayBean.code != 0) {
                throw ApiException(baseArrayBean.code, baseArrayBean.message)
            }
            gson.fromJson(baseArrayBean.data, type)
        } finally {
            value.close()
        }
    }
}