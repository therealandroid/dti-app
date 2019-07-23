package com.therealdroid.github.company.register

/**
 * This form state is the same as login
 * but in a real situation it will have more parameters.
 *
 */
data class RegisterFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)
