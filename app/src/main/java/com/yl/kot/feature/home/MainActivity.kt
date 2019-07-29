package com.yl.kot.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.yl.kot.R
import com.yl.kot.data.entity.Banner
import com.yl.kot.feature.login.LoginActivity
import com.yl.kot.utils.SingleToast

class MainActivity : AppCompatActivity(), HomeContract.View {
    private val mHomePresenter: HomeContract.Presenter by lazy {
        HomePresenter(this)
    }

    private lateinit var mBtnJumpLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBtnJumpLogin = findViewById(R.id.btn_jump_login)

        mBtnJumpLogin.setOnClickListener {
            startActivityForResult(
                Intent(this, LoginActivity::class.java),
                REQUEST_CODE_LOGIN
            )
        }

        mHomePresenter.getBanner()
    }

    override fun showBanner(bannerList: List<Banner>) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_LOGIN) {
            if (resultCode != RESULT_OK) return
            val loginResult = LoginActivity.getLoginResult(data)
            if (loginResult.isLoginSuccess) {
                mBtnJumpLogin.visibility = View.GONE
                SingleToast.showToast("欢迎你，" + loginResult.user!!.nickname)
            } else {
                mBtnJumpLogin.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_LOGIN = 0x01
    }
}
