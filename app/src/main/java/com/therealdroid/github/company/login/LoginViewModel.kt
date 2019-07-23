package com.therealdroid.github.company.login

import com.therealdroid.github.company.R
import com.therealdroid.github.company.register.RegisterRepository
import com.therealdroid.github.network.implementation.login.models.ApiResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val loginRepository: LoginRepository) {

    fun login(username: String, password: String): Single<ApiResponse> {
        return loginRepository.login(username, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    //Client side validation
    fun formDataChanged(username: String, password: String): Observable<LoginFormState> {
        return if (!isUserNameValid(username)) {
            Observable.just(LoginFormState(usernameError = R.string.invalid_username))
        } else if (!isPasswordValid(password)) {
            Observable.just(LoginFormState(passwordError = R.string.invalid_password))
        } else {
            Observable.just(LoginFormState(isDataValid = true))
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return username.length in 5..59
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length in 3..1024
    }

}