package com.therealdroid.github.company.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dti.persistency.implementation.login.IUserPersistenceDataSource
import com.dti.persistency.implementation.login.UserPersistenceDataSource
import com.therealdroid.github.company.R
import com.therealdroid.github.company.extensions.afterTextChanged
import com.therealdroid.github.company.extensions.hideSoftKeyboard
import com.therealdroid.github.company.extensions.loadImage
import com.therealdroid.github.company.home.HomeActivity
import com.therealdroid.github.company.register.RegisterActivity
import com.therealdroid.github.network.implementation.login.AuthenticationNetworkDataSource
import com.therealdroid.github.network.implementation.login.IAuthenticationDataSource
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private var loginViewModel = LoginViewModel(
        LoginRepository(
            AuthenticationNetworkDataSource(),
            UserPersistenceDataSource()
        )
    )

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
                        doLogin()
                    }
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                hideSoftKeyboard()
                doLogin()
            }

            register.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }

    private fun doLogin() {
        compositeDisposable.add(loginViewModel.login(username.text.toString(), password.text.toString())
            .subscribe(
                {
                    if (it.success) {
                        //if we got success redirect to home
                        showLoginSuccess(it.message)
                    } else {
                        showLoginFailed(it.message)
                    }
                }, {
                    //An unhandled server parsing or network exception occurred
                    //developer error
                    //An unhandled server parsing or network exception occurred
                    //internet connection
                    showLoginFailed(getString(R.string.server_unavailable))
                })
        )
    }

    private fun checkErrors(username: String, password: String) {
        loginViewModel.formDataChanged(
            username,
            password
        ).subscribe {
            displayErrors(it)
        }.dispose()
    }

    private fun displayErrors(loginState: LoginFormState) {
        // disable login button unless both username / password is valid
        login.isEnabled = loginState.isDataValid

        if (loginState.usernameError != null) {
            username.error = getString(loginState.usernameError)
        }

        if (loginState.passwordError != null) {
            password.error = getString(loginState.passwordError)
        }
    }

    private fun showLoginSuccess(successString: String) {
        loading.visibility = View.INVISIBLE
        Toast.makeText(applicationContext, successString, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, HomeActivity::class.java).apply {
            putExtra("username", successString)
        })

        this.finish()
    }

    private fun showLoginFailed(errorString: String) {
        loading.visibility = View.INVISIBLE
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()//clear future requests to avoid null pointers
    }
}
