package com.yl.kot.feature.login

import com.yl.kot.R
import com.yl.kot.base.BasePresenter
import com.yl.kot.data.entity.User
import com.yl.kot.data.remote.DataManager
import com.yl.kot.data.remote.RemoteDataObserver

/**
 * Created on 2019/7/27.
 *
 * @author lshun
 * @version 1.0
 */
class LoginPresenter(view: LoginContract.View) : BasePresenter<LoginContract.View>(view),
    LoginContract.Presenter {

    /**
     * 用户登录
     *
     * @param username   用户名
     * @param password   密码
     */
    override fun login(username: String?, password: String?) {
        val checkUsernameResult: String? = checkUsername(username)
        if (checkUsernameResult != null) {
            mView?.showUsernameError(checkUsernameResult)
            return
        }

        val checkPasswordResult: String? = checkPassword(password)
        if (checkPasswordResult != null) {
            mView?.showPasswordError(checkPasswordResult)
            return
        }

        DataManager.login(username!!, password!!)
            .subscribe(object : RemoteDataObserver<User>(this) {
                override fun onNext(t: User) {
                    mView?.showLoginResult(t)
                }

                override fun onError(throwable: Throwable) {
                    super.onError(throwable)
                    mView?.showLoginResult(null)
                }
            })
    }

    /**
     * 用户注册
     *
     * @param username   用户名
     * @param password   密码
     * @param rePassword 密码重复确认
     */
    override fun register(username: String?, password: String?, rePassword: String?) {
        val checkUsernameResult: String? = checkUsername(username)
        if (checkUsernameResult != null) {
            mView?.showUsernameError(checkUsernameResult)
            return
        }

        val checkPasswordResult: String? = checkPassword(password)
        if (checkPasswordResult != null) {
            mView?.showPasswordError(checkPasswordResult)
            return
        }

        val checkRePasswordResult: String? = checkRePassword(password, rePassword)
        if (checkRePasswordResult != null) {
            mView?.showRePasswordError(checkRePasswordResult)
            return
        }

        DataManager.register(username!!, password!!, rePassword!!)
            .subscribe(object : RemoteDataObserver<User>(this) {
                override fun onNext(t: User) {
                    mView?.showRegisterResult(t)
                }

                override fun onError(throwable: Throwable) {
                    super.onError(throwable)
                    mView?.showRegisterResult(null)
                }
            })
    }

    /**
     * 判断用户名是否合法
     *
     * @param username   用户名
     * @return 合法时返回<code>NULL</code>，不合法返回错误信息
     */
    override fun checkUsername(username: String?): String? {
        if (username == null || username.isEmpty()) return mView!!.getStringValue(R.string.error_empty_username)
        if (username.length < 4 || username.length > 16) {
            return mView!!.getStringValue(R.string.error_username_length, 4, 16)
        }
        return null
    }

    /**
     * 判断密码是否合法
     *
     * @param password   密码
     * @return 合法时返回<code>NULL</code>，不合法返回错误信息
     */
    override fun checkPassword(password: String?): String? {
        if (password == null || password.isEmpty()) return mView!!.getStringValue(R.string.error_empty_password)
        if (password.length < 4 || password.length > 16) {
            return mView!!.getStringValue(R.string.error_password_length, 4, 16)
        }
        return null
    }

    /**
     * 判断确认密码是否合法，仅判断它与第一次输入的密码是否相同即可
     *
     * @param password   密码
     * @param rePassword 密码重复确认
     * @return 合法时返回<code>NULL</code>，不合法返回错误信息
     */
    override fun checkRePassword(password: String?, rePassword: String?): String? {
        return if (password == rePassword) {
            null
        } else {
            mView!!.getStringValue(R.string.error_twice_password_not_equals)
        }
    }
}