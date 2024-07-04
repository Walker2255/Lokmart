package com.azimjonc.projects.lookmart.presentation.sign_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azimjonc.projects.lookmart.data.repo.AuthRepositoryImpl
import com.azimjonc.projects.lookmart.data.store.TokenStore
import com.azimjonc.projects.lookmart.domain.repo.AuthRepository
import com.azimjonc.projects.lookmart.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    //  Progress uchun, sign in bosilganda progress chiqarishi uchun
    val loading = MutableLiveData(false)
    val events = SingleLiveEvent<Event>()

    //    SignInfragment dan ma'lumotlani olib kerluvchi funksiya
    //    suspend funcsiyani chaqirish uchun coroutine scope ishalatamiz
    fun signIn(username: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        loading.postValue(true)
        try {
            authRepository.signIn(username, password)
        } catch (e: Exception) {
            when {
                e is HttpException && e.code() === 404 -> events.postValue(Event.InvalidCredentials)
                e is IOException -> events.postValue(Event.ConnectionError)
                else -> events.postValue(Event.Error)
            }
        } finally {
            loading.postValue(false)
        }
    }

    //    eventlarni bo'lib olish uchun
    sealed class Event {
        object InvalidCredentials : Event()
        object ConnectionError : Event()
        object Error : Event()
    }

}