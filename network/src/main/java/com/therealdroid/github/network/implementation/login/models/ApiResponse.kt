package com.therealdroid.github.network.implementation.login.models

import com.google.gson.annotations.SerializedName

data class ApiResponse(@SerializedName("errorMessage") var message: String, var success: Boolean)