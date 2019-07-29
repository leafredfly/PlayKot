package com.yl.kot.feature.login

import com.yl.kot.base.IBasePresenter
import com.yl.kot.base.IBaseView
import com.yl.kot.data.entity.User

/**
 * Created on 2019/7/27.
 *
 * @author lshun
 * @version 1.0
 */
interface LoginContract {
    interface View : IBaseView {
        /**
         * 显示username错误
         *
         * @param errorMsg 错误信息
         */
        fun showUsernameError(errorMsg: String)

        /**
         * 显示password错误
         *
         * @param errorMsg 错误信息
         */
        fun showPasswordError(errorMsg: String)

        /**
         * 显示rePassword错误
         *
         * @param errorMsg 错误信息
         */
        fun showRePasswordError(errorMsg: String)

        /**
         * 显示登录结果
         *
         * @param user 成功时返回用户信息，失败返回<code>NULL</code>
         */
        fun showLoginResult(user: User?)

        /**
         * 显示注册结果
         *
         * @param user 成功时返回用户信息，失败返回<code>NULL</code>
         */
        fun showRegisterResult(user: User?)
    }

    interface Presenter : IBasePresenter<View> {
        /**
         * 用户登录
         *
         * @param username   用户名
         * @param password   密码
         */
        fun login(username: String?, password: String?)

        /**
         * 用户注册
         *
         * @param username   用户名
         * @param password   密码
         * @param rePassword 密码重复确认
         */
        fun register(username: String?, password: String?, rePassword: String?)

        /**
         * 判断用户名是否合法
         *
         * @param username   用户名
         * @return 合法时返回<code>NULL</code>，不合法返回错误信息
         */
        fun checkUsername(username: String?): String?

        /**
         * 判断密码是否合法
         *
         * @param password   密码
         * @return 合法时返回<code>NULL</code>，不合法返回错误信息
         */
        fun checkPassword(password: String?): String?

        /**
         * 判断确认密码是否合法，仅判断它与第一次输入的密码是否相同即可
         *
         * @param password   密码
         * @param rePassword 密码重复确认
         * @return 合法时返回<code>NULL</code>，不合法返回错误信息
         */
        fun checkRePassword(password: String?, rePassword: String?): String?
    }
}