package com.mohsen.itollhub.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohsen.itollhub.data.repository.UserRepository
import com.mohsen.itollhub.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _state = MutableStateFlow(
        SearchScreenState(
            isLoading = false,
            description = "iTollHub",
            users = listOf()
        )
    )
    val state = _state.asStateFlow()

    fun searchUser(searchQuery: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true, description = "Searching...", users = listOf())
            }
            repository.searchUser(searchQuery).onSuccess {
                if (it.isNotEmpty()) {
                    _state.update { state ->
                        state.copy(isLoading = false, users = it)
                    }
                } else {
                    _state.update { state ->
                        state.copy(
                            isLoading = false,
                            description = "No user found",
                            users = listOf()
                        )
                    }
                }
            }.onFailure {
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        description = it.message ?: "Unknown error occurred",
                        users = listOf()
                    )
                }
            }
        }
    }
}

data class SearchScreenState(
    val isLoading: Boolean,
    val description: String,
    val users: List<User>
)