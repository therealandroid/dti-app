package com.therealdroid.github.company.register

import com.therealdroid.github.company.R
import com.therealdroid.github.company.login.LoginFormState
import com.therealdroid.github.network.implementation.login.models.ApiResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RegisterViewModel(private val registerRepository: RegisterRepository) {

    fun register(username: String, password: String): Single<ApiResponse> {
        return registerRepository.register(username, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    //Client side validation
    fun formDataChanged(username: String, password: String): Observable<RegisterFormState> {
        return if (!isUserNameValid(username)) {
            Observable.just(RegisterFormState(usernameError = R.string.invalid_username))
        } else if (!isPasswordValid(password)) {
            Observable.just(RegisterFormState(passwordError = R.string.invalid_password))
        } else {
            Observable.just(RegisterFormState(isDataValid = true))
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return username.length in 5..59
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length in 3..1024
    }

}