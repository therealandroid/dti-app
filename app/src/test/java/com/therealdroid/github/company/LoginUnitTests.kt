package com.therealdroid.github.company

import com.dti.persistency.implementation.login.UserPersistenceDataSource
import com.therealdroid.github.company.login.LoginRepository
import com.therealdroid.github.company.login.LoginViewModel
import com.therealdroid.github.network.implementation.login.AuthenticationNetworkDataSource
import com.therealdroid.github.network.implementation.login.models.ApiResponse
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner
import org.powermock.api.mockito.PowerMockito.`when`

@RunWith(MockitoJUnitRunner::class)
class LoginUnitTests {

    lateinit var loginViewModel: LoginViewModel

    //It is very common to pass different data sources to repository to test different implementations, but in my case
    //I had to brief this test

    @Mock
    lateinit var loginRepository: LoginRepository

    var networkDataSource = AuthenticationNetworkDataSource()

    var persistenceDatasource = UserPersistenceDataSource()

    @Before
    fun setup() {
        ImediateSchedulers.setImediateExecutor()
        MockitoAnnotations.initMocks(this)
        loginRepository = LoginRepository(networkDataSource, persistenceDatasource)
        loginViewModel = LoginViewModel(loginRepository)
    }

    @Test
    fun test_when_login_fail_should_get_correct_message(){
        val inValidResponse = ApiResponse("", false)

        val invalidUsername = "exemplo3"
        val invalidPassword = "exemplo3"

        `when`(loginRepository.login(invalidUsername, invalidPassword))
            .thenReturn(Single.just(inValidResponse))


        loginViewModel.login(invalidUsername, invalidPassword).test().assertValue {
            it.message == "Service is currently unavailable. Try again later."
        }
    }

    @Test
    fun test_should_log_in_fail(){
        val inValidResponse = ApiResponse("", false)

        val invalidUsername = "exemplo3"
        val invalidPassword = "exemplo3"

        `when`(loginRepository.login(invalidUsername, invalidPassword))
            .thenReturn(Single.just(inValidResponse))


        loginViewModel.login(invalidUsername, invalidPassword).test().assertValue {
            !it.success
        }
    }

    @Test
    fun test_should_log_in_success() {
        val validResponse = ApiResponse("", true)

        val validUsername = "exemplo"
        val validPassword = "exemplo"

        `when`(loginRepository.login(validUsername, validPassword))
            .thenReturn(Single.just(validResponse))

        loginViewModel.login(validUsername, validPassword).test().assertValue {
            it.success
        }
    }
}