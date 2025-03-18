package com.example.instagramclone.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramclone.data.entity.Post
import com.example.instagramclone.data.entity.User
import com.example.instagramclone.data.local.InstagramCloneDatabase
import com.example.instagramclone.data.repository.PostRepository
import com.example.instagramclone.data.repository.UserRepository
import com.example.instagramclone.model.Event
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
	internal val userRepository: UserRepository,
	internal val postRepository: PostRepository,
	private val database: InstagramCloneDatabase
) : ViewModel() {
	
	val signedIn = mutableStateOf(false)
	val isLoading = mutableStateOf(false)
	val currentUser = mutableStateOf<User?>(null)
	val popUpNotification = mutableStateOf<Event<String>?>(null)
	val userPosts = postRepository.userPosts(userId = currentUser.value?.id ?: 1)
	
	init {
		
		viewModelScope.launch {
			val userId = async { userRepository.getUserLoggedInId() }.await()
			userId?.let {
				loadUserDataAndLogIn(it)
			}
		}
	}
	
	fun onDestroy() {
		try {
			database.close()
			Log.d("Database", "Database closed!")
		} catch (exception: Exception) {
			Log.d("Database", "Database error to close!")
		}
	}
}

//<editor-fold desc="Error Handler">
fun InstagramViewModel.handleSqlException(exception: Exception) {
	if (exception.message.toString().contains("users.username")) {
		showMessage("Username already exists.")
	} else if (exception.message.toString().contains("users.email")) {
		showMessage("Email already exists.")
	} else {
		showMessage(exception.message.toString())
	}
}

fun InstagramViewModel.showMessage(value: String) {
	popUpNotification.value = Event(value)
}
//</editor-fold>

//<editor-fold desc="Post">
fun InstagramViewModel.createNewPost(imageUrl: String, description: String, onSuccess: () -> Unit) {
	if (description.isEmpty()) {
		showMessage("Description can't be empty!")
		return
	}
	currentUser.value?.let { user ->
		val post = Post(
			userId = user.id,
			imageUrl = imageUrl,
			description = description
		)
		
		isLoading.value = true
		viewModelScope.launch {
			try {
				val result = postRepository.insert(post)
				if (result > 0) {
					val postId = result.toInt()
					post.id = postId
					showMessage("Post successfully created!")
					onSuccess()
				}
				isLoading.value = false
			} catch (exception: Exception) {
				showMessage("Can't create post.")
				isLoading.value = false
			}
		}
	}
}

fun InstagramViewModel.deleteAllPosts() {
	viewModelScope.launch {
		postRepository.deleteAllPosts()
	}
}
//</editor-fold>

//<editor-fold desc="User">
fun InstagramViewModel.loadUserDataAndLogIn(userId: Int) {
	isLoading.value = true
	viewModelScope.launch {
		userRepository.getUserById(userId)?.let {
			currentUser.value = it
			signedIn.value = true
			isLoading.value = false
		}
	}
}


fun InstagramViewModel.updateUser(
	name: String = currentUser.value?.name ?: "",
	username: String = currentUser.value?.username ?: "",
	bio: String = currentUser.value?.bio ?: "",
	imageUrl: String? = currentUser.value?.imageUrl,
	onFinish: () -> Unit = {}
) {
	currentUser.value?.let {
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
					currentUser.value = userToUpdate
					onFinish()
				} else {
					showMessage("Can't update user.")
				}
				isLoading.value = false
			} catch (exception: Exception) {
				handleSqlException(exception = exception)
				isLoading.value = false
			}
		}
	} ?: {
		showMessage("User not found!")
	}
}
//</editor-fold>

//<editor-fold desc="Auth">
fun InstagramViewModel.onSignUp(name: String, username: String, email: String, password: String) {
	if (name.isEmpty() or username.isEmpty() or email.isEmpty() or password.isEmpty()) {
		showMessage("All fields are required!")
		return
	}
	
	isLoading.value = true
	
	viewModelScope.launch {
		val userToInsert =
			User(name = name, username = username, email = email, password = password)
		try {
			var result = userRepository.insert(userToInsert)
			val userId = result.toInt()
			userRepository.getUserById(id = userId).let {
				currentUser.value = it
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

fun InstagramViewModel.onLogin(
	email: String,
	password: String
) {
	if (email.isEmpty() or password.isEmpty()) {
		showMessage("All fields are required!")
		return
	}
	isLoading.value = true
	
	viewModelScope.launch {
		userRepository.getUserByEmailAndPassword(email, password)?.let {
			currentUser.value = it
			isLoading.value = false
			signedIn.value = true
			userRepository.onLogin(it.id)
		} ?: run {
			showMessage("Login Error!")
			isLoading.value = false
		}
	}
}

fun InstagramViewModel.onLogout() {
	viewModelScope.launch {
		async { userRepository.onLogout() }.await()
		currentUser.value = null
		signedIn.value = false
		popUpNotification.value = Event("Logged out!")
	}
}
//</editor-fold>