package com.therealdroid.github.company

import com.therealdroid.github.company.register.RegisterRepository
import com.therealdroid.github.company.register.RegisterViewModel
import com.therealdroid.github.network.implementation.login.AuthenticationNetworkDataSource
import com.therealdroid.github.network.implementation.login.models.ApiResponse
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner
import org.powermock.api.mockito.PowerMockito.`when`

@RunWith(MockitoJUnitRunner::class)
class RegisterUnitTests {

    lateinit var registerViewModel: RegisterViewModel

    //It is very common to pass different data sources to repository to test different implementations, but in my case
    //I had to brief this test

    @Mock
    lateinit var registerRepository: RegisterRepository


    @Before
    fun setup() {
        ImediateSchedulers.setImediateExecutor()
        MockitoAnnotations.initMocks(this)
        registerRepository = RegisterRepository(AuthenticationNetworkDataSource())
        registerViewModel = RegisterViewModel(registerRepository)
    }

    @Test
    fun test_register_should_fail() {
        val inValidResponse = ApiResponse("", false)

        val invalidUsername = "exemplo3"
        val invalidPassword = "exemplo3"

        `when`(registerViewModel.register(invalidUsername, invalidPassword))
            .thenReturn(Single.just(inValidResponse))


        registerViewModel.register(invalidUsername, invalidPassword).test().assertValue {
            it.message == "Service is currently unavailable. Try again later."
        }
    }

    @Test
    fun test_register_should_success() {
        val validResponse = ApiResponse("", true)

        val validUsername = "exemplo"
        val validPassword = "exemplo"

        `when`(registerRepository.register(validUsername, validPassword))
            .thenReturn(Single.just(validResponse))

        registerViewModel.register(validUsername, validPassword).test().assertValue {
            it.success
        }
    }

}