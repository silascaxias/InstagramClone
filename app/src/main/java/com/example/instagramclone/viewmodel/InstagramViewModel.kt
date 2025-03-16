package com.example.instagramclone.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramclone.data.model.Event
import com.example.instagramclone.data.model.User
import com.example.instagramclone.data.repository.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * InstagramViewModel
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

class InstagramViewModel(
	private val userRepository: UserRepository
) : ViewModel() {
	
	val signedIn = mutableStateOf(false)
	val isLoading = mutableStateOf(false)
	val user = mutableStateOf<User?>(null)
	val popUpNotification = mutableStateOf<Event<String>?>(null)
	
	init {
		viewModelScope.launch {
			val userId = async { userRepository.getUserLoggedInId() }.await()
			userId?.let {
				loadUserDataAndLogIn(it)
			}
		}
	}
	
	fun onSignUp(name: String, username: String, email: String, password: String) {
		if (name.isEmpty() or username.isEmpty() or email.isEmpty() or password.isEmpty()) {
			handleException("All fields are required!")
			return
		}
		
		isLoading.value = true
		
		viewModelScope.launch {
			val userToInsert =
				User(id = 0, name = name, username = username, email = email, password = password)
			try {
				var result = userRepository.insert(userToInsert)
				val userId = result.toInt()
				userRepository.getUserById(id = userId).let {
					user.value = it
					userRepository.onLogin(userId)
					isLoading.value = false
					signedIn.value = true
				}
			} catch (exception: Exception) {
				handleSqlException(exception = exception)
				isLoading.value = false
			}
			
		}
	}
	
	private fun handleSqlException(exception: Exception) {
		if (exception.message.toString().contains("users.username")) {
			handleException("Username already exists.")
		} else if (exception.message.toString().contains("users.email")) {
			handleException("Email already exists.")
		} else {
			handleException(exception.message.toString())
		}
	}
	
	fun onLogin(
		email: String,
		password: String
	) {
		if (email.isEmpty() or password.isEmpty()) {
			handleException("All fields are required!")
			return
		}
		isLoading.value = true
		
		viewModelScope.launch {
			userRepository.getUserByEmailAndPassword(email, password)?.let {
				user.value = it
				isLoading.value = false
				signedIn.value = true
				userRepository.onLogin(it.id)
			} ?: run {
				handleException("Login Error!")
				isLoading.value = false
			}
		}
	}
	
	fun loadUserDataAndLogIn(userId: Int) {
		isLoading.value = true
		viewModelScope.launch {
			userRepository.getUserById(userId)?.let {
				user.value = it
				signedIn.value = true
				isLoading.value = false
			}
		}
	}
	
	fun updateUser(
		name: String = user.value?.name ?: "",
		username: String = user.value?.username ?: "",
		bio: String = user.value?.bio ?: "",
		imageUrl: String? = user.value?.imageUrl,
		onFinish: () -> Unit = {}
	) {
		user.value?.let {
			val userToUpdate = it
			userToUpdate.name = name
			userToUpdate.username = username
			userToUpdate.bio = bio
			imageUrl?.let {
				userToUpdate.imageUrl = it
			}
			
			isLoading.value = true
			viewModelScope.launch {
				try {
					val result = userRepository.update(userToUpdate)
					if (result > 0) {
						user.value = userToUpdate
						onFinish()
					} else {
						handleException("Can't update user.")
					}
					isLoading.value = false
				} catch (exception: Exception) {
					handleSqlException(exception = exception)
					isLoading.value = false
				}
			}
		} ?: {
			handleException("User not found!")
		}
	}
	
	fun handleException(message: String) {
		popUpNotification.value = Event(message)
	}
	
	fun onLogout() {
		viewModelScope.launch {
			async { userRepository.onLogout() }
			signedIn.value = false
		}
	}
	
	fun onDestroy() {
		userRepository.onDestroy()
	}
}