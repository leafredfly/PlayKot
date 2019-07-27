package com.yl.kot.feature.login

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.yl.kot.R
import com.yl.kot.base.BaseActivity
import com.yl.kot.data.entity.User
import com.yl.kot.utils.SingleToast

class LoginActivity : BaseActivity(), LoginContract.View {
    private val mPresenter: LoginContract.Presenter by lazy {
        LoginPresenter(this)
    }
    private lateinit var mUsernameInputLayout: TextInputLayout
    private lateinit var mPasswordInputLayout: TextInputLayout
    private lateinit var mEtUsername: TextInputEditText
    private lateinit var mEtPassword: TextInputEditText
    private lateinit var mBtnGo: Button
    private lateinit var mBtnSwitchMode: Button

    private var mIsLoginMode: Boolean = true

    private val mSharkAnim: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.anim_shake)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        mUsernameInputLayout = findViewById(R.id.input_layout_username)
        mPasswordInputLayout = findViewById(R.id.input_layout_password)
        mEtUsername = findViewById(R.id.edt_username)
        mEtPassword = findViewById(R.id.edt_password)
        mBtnGo = findViewById(R.id.btn_go)
        mBtnSwitchMode = findViewById(R.id.btn_switch_mode)

        mEtUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                mUsernameInputLayout.isErrorEnabled = false
            }
        })

        mEtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                mPasswordInputLayout.isErrorEnabled = false
            }
        })

        mEtPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                clickGo()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        mBtnGo.setOnClickListener { clickGo() }
        mBtnSwitchMode.setOnClickListener {
            mIsLoginMode = !mIsLoginMode
            setLoginMode(mIsLoginMode)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mEtUsername.clearAnimation()
        mEtPassword.clearAnimation()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setLoginMode(savedInstanceState.getBoolean(STATE_KEY_IS_LOGIN_MODE))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(STATE_KEY_IS_LOGIN_MODE, mIsLoginMode)
    }

    override fun showUsernameError(errorMsg: String) {
        mUsernameInputLayout.isErrorEnabled = true
        mUsernameInputLayout.error = errorMsg
        mEtUsername.requestFocus()
        mEtUsername.clearAnimation()
        mEtUsername.startAnimation(mSharkAnim)
    }

    override fun showPasswordError(errorMsg: String) {
        mPasswordInputLayout.isErrorEnabled = true
        mPasswordInputLayout.error = errorMsg
        mEtPassword.requestFocus()
        mEtPassword.clearAnimation()
        mEtPassword.startAnimation(mSharkAnim)
    }

    override fun showRePasswordError(errorMsg: String) {
    }

    override fun showLoginResult(user: User?) {
        //TODO replace with dismiss loading dialog
        mBtnGo.isEnabled = true

        if (user == null) {
            SingleToast.showToast(R.string.login_failed)
            return
        }

        val intent = Intent()
        intent.putExtra(EXTRA_IS_LOGIN_SUCCESS, true)
        intent.putExtra(EXTRA_IS_REGISTER, false)
        intent.putExtra(EXTRA_USER, user as Parcelable)
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun showRegisterResult(user: User?) {
        //TODO replace with dismiss loading dialog
        mBtnGo.isEnabled = true

        if (user == null) {
            SingleToast.showToast(R.string.register_failed)
            return
        }

        val intent = Intent()
        intent.putExtra(EXTRA_IS_LOGIN_SUCCESS, true)
        intent.putExtra(EXTRA_IS_REGISTER, true)
        intent.putExtra(EXTRA_USER, user as Parcelable)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun clickGo() {
        val username: String? = mEtUsername.text?.toString()
        val password: String? = mEtPassword.text?.toString()

        //valid username
        val checkUsernameResult: String? = mPresenter.checkUsername(username)
        if (checkUsernameResult != null) {
            showUsernameError(checkUsernameResult)
            return
        }

        //valid password
        val checkPasswordResult: String? = mPresenter.checkPassword(password)
        if (checkPasswordResult != null) {
            showPasswordError(checkPasswordResult)
            return
        }

        //TODO replace with show loading dialog
        mBtnGo.isEnabled = false

        if (mIsLoginMode) {
            mPresenter.login(username, password)
        } else {
            //val rePassword: String? = mEtPassword.text?.toString()
            mPresenter.register(username, password, password)
        }
    }

    private fun setLoginMode(isLoginMode: Boolean) {
        if (isLoginMode) {
            setTitle(R.string.user_login)
            mEtPassword.setImeActionLabel(getString(R.string.login), EditorInfo.IME_ACTION_GO)
            mBtnGo.setText(R.string.login)
            mBtnSwitchMode.setText(R.string.switch_register)
        } else {
            setTitle(R.string.user_register)
            mEtPassword.setImeActionLabel(
                getString(R.string.register),
                EditorInfo.IME_ACTION_GO
            )
            mBtnGo.setText(R.string.register)
            mBtnSwitchMode.setText(R.string.switch_login)
        }
        //在横屏状态下，当前焦点在密码输入框时，失去焦点后重新获取来触发输入法更新显示ImeActionLabel
        /*if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE &&
            window.decorView.findFocus().id == mEtPassword.id
        ) {
            mEtUsername.requestFocus()
            mEtUsername.post { mEtPassword.requestFocus() }
        }*/
    }

    companion object {
        const val REQUEST_CODE_LOGIN: Int = 0x20190728
        private const val STATE_KEY_IS_LOGIN_MODE = "isLoginMode"
        private const val EXTRA_IS_LOGIN_SUCCESS = "isLoginSuccess"
        private const val EXTRA_IS_REGISTER = "isRegister"
        private const val EXTRA_USER = "user"

        /**
         * 将onActivityResult回调中的Intent数据解析成LoginResult
         */
        fun getLoginResult(data: Intent?): LoginResult {
            if (data == null) return LoginResult(false, null, null)

            val isLoginSuccess = data.getBooleanExtra(EXTRA_IS_LOGIN_SUCCESS, false)
            val isRegister: Boolean? = if (isLoginSuccess) {
                data.getBooleanExtra(EXTRA_IS_REGISTER, false)
            } else {
                null
            }
            val user: User? = if (isLoginSuccess) {
                data.getParcelableExtra(EXTRA_USER)
            } else {
                null
            }
            return LoginResult(isLoginSuccess, isRegister, user)
        }

        data class LoginResult constructor(
            /**
             * 是否登录成功
             */
            var isLoginSuccess: Boolean,
            /**
             * 是否是注册
             *
             * 未登录成功时返回<code>NULL</code>
             */
            var isRegister: Boolean?,
            /**
             * 登录成功后的用户信息
             *
             * 未登录成功时返回<code>NULL</code>
             */
            var user: User? = null
        )
    }
}
