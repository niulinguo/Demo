package com.lingo.kotlin.app.modules.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lingo.kotlin.app.BuildConfig
import com.lingo.kotlin.app.R
import com.lingo.kotlin.app.api.WanAndroidAPI
import com.lingo.kotlin.app.entity.LoginResponseData
import com.lingo.kotlin.app.network.APIClient
import com.lingo.kotlin.app.network.APIResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginActivity : AppCompatActivity() {
    private val usernameEditView: EditText by lazy {
        findViewById(R.id.edit_username)
    }

    private val passwordEditView: EditText by lazy {
        findViewById(R.id.edit_password)
    }

    private val loginButton: Button by lazy {
        findViewById(R.id.btn_login)
    }

    private val api: WanAndroidAPI by lazy {
        APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (BuildConfig.WanAndroidUserInfo.isNotBlank()) {
            val split = BuildConfig.WanAndroidUserInfo.split("/")
            usernameEditView.setText(split[0])
            passwordEditView.setText(split[1])
        }

        loginButton.setOnClickListener {
            val username: String = usernameEditView.text.toString()
            val password: String = passwordEditView.text.toString()
            loginAction(username, password)
        }
    }

    private fun loginAction(username: String, password: String) {
        api.loginAction(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : APIResponse<LoginResponseData>() {
                override fun success(data: LoginResponseData) {
                    Toast.makeText(this@LoginActivity, "登录成功 ${data.username}", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun failure(msg: String) {
                    Toast.makeText(this@LoginActivity, msg, Toast.LENGTH_SHORT).show()
                }
            })
    }
}