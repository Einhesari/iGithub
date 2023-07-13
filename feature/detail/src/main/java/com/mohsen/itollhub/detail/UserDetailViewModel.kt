package com.mohsen.itollhub.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohsen.itollhub.data.repository.UserRepository
import com.mohsen.itollhub.model.UserDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val repository: UserRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(
        UserDetailScreenState(
            isLoading = true,
            description = "Loading user details...",
            user = null
        )
    )
    val state = _state.asStateFlow()

    fun getUserDetail(userName: String) {
        _state.update {
            it.copy(
                isLoading = true,
                description = "Loading user details...",
                user = null
            )
        }
        viewModelScope.launch {
            repository.getUserDetail(userName)
                .onSuccess {
                    _state.update { state ->
                        state.copy(isLoading = false, user = it)
                    }
                }
                .onFailure {
                    _state.update { state ->
                        state.copy(
                            isLoading = false,
                            description = it.message ?: "Unknown error occurred",
                            user = null
                        )
                    }
                }
        }
    }
}

data class UserDetailScreenState(
    val isLoading: Boolean,
    val description: String,
    val user: UserDetail?
)