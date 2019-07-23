package com.therealdroid.github.company.register

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.therealdroid.github.company.R
import com.therealdroid.github.company.extensions.afterTextChanged
import com.therealdroid.github.company.extensions.hideSoftKeyboard
import com.therealdroid.github.company.login.LoginActivity
import com.therealdroid.github.network.implementation.login.AuthenticationNetworkDataSource
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    private var registerViewModel = RegisterViewModel(RegisterRepository(AuthenticationNetworkDataSource()))
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(toolbarRegister)

        supportActionBar?.setTitle(R.string.title_activity_register)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        username.afterTextChanged {
            checkErrors(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                checkErrors(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        loading.visibility = View.VISIBLE
                        hideSoftKeyboard()
                        doRegister()
                    }

                }
                false
            }

            register.setOnClickListener {
                loading.visibility = View.VISIBLE
                hideSoftKeyboard()
                doRegister()
            }

        }
    }

    private fun doRegister() {
        compositeDisposable.add(
            registerViewModel.register(username.text.toString(), password.text.toString()).subscribe(
                {
                    if (it.success) {
                        showRegisterSuccess(it.message)
                    } else {
                        showRegisterFailed(it.message)
                    }
                },
                {
                    //developer error
                    //An unhandled server parsing or network exception occurred
                    //internet connection
                    showRegisterSuccess(getString(R.string.server_unavailable))
                })
        )
    }

    private fun checkErrors(username: String, password: String) {
        registerViewModel.formDataChanged(
            username,
            password
        ).subscribe {
            displayErrors(it)
        }.dispose()
    }

    private fun displayErrors(loginState: RegisterFormState) {
        register.isEnabled = loginState.isDataValid

        if (loginState.usernameError != null) {
            username.error = getString(loginState.usernameError)
        }

        if (loginState.passwordError != null) {
            password.error = getString(loginState.passwordError)
        }
    }

    private fun showRegisterSuccess(successString: String) {
        Toast.makeText(applicationContext, successString, Toast.LENGTH_SHORT).show()
        loading.visibility = View.INVISIBLE
        startActivity(Intent(this, LoginActivity::class.java))
        this.finish()
    }

    private fun showRegisterFailed(errorString: String) {
        loading.visibility = View.INVISIBLE
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            super.onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()//clear future requests to avoid null pointers
    }
}
