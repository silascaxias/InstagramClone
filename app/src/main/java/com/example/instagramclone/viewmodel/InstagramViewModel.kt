package com.example.instagramclone.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.instagramclone.data.Event
import com.example.instagramclone.data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * InstagramViewModel
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

const val USERS = "users"

@HiltViewModel
class InstagramViewModel @Inject constructor(
	val auth: FirebaseAuth, val db: FirebaseFirestore, val storage: FirebaseStorage
) : ViewModel() {
	
	val signedIn = mutableStateOf(false)
	val isLoading = mutableStateOf(false)
	val userData = mutableStateOf<UserData?>(null)
	val popUpNotification = mutableStateOf<Event<String>?>(null)
	
	init {
		val currentUser = auth.currentUser
		signedIn.value = currentUser != null
		currentUser?.uid?.let { uid ->
			getUserData(uid)
		}
	}
	
	fun onSignUp(username: String, email: String, password: String) {
		if (username.isEmpty() or email.isEmpty() or password.isEmpty()) {
			handleException(customMessage = "All fields are required!")
			return
		}
		isLoading.value = true
		
		db.collection(USERS).whereEqualTo("username", username).get()
			.addOnSuccessListener { documents ->
				if (documents.size() > 0) {
					handleException(customMessage = "Username already exists")
					isLoading.value = false
				} else {
					auth.createUserWithEmailAndPassword(email, password)
						.addOnCompleteListener { task ->
							isLoading.value = false
							if (task.isSuccessful) {
								createOrUpdateProfile(username = username)
							} else {
								handleException(
									exception = task.exception, customMessage = "Sign up error"
								)
							}
						}
				}
			}
	}
	
	fun onLogin(
		email: String,
		password: String
	) {
		if (email.isEmpty() or password.isEmpty()) {
			handleException(customMessage = "All fields are required!")
			return
		}
		isLoading.value = true
		auth.signInWithEmailAndPassword(email, password)
			.addOnCompleteListener { task ->
				isLoading.value = false
				if (task.isSuccessful) {
					signedIn.value = true
					auth.currentUser?.uid?.let { uid ->
						getUserData(uid)
					}
				} else {
					handleException(
						exception = task.exception, customMessage = "Login Error!"
					)
				}
			}
			.addOnFailureListener { exception ->
				isLoading.value = false
				handleException(exception = exception, customMessage = "Login error!")
			}
	}
	
	fun createOrUpdateProfile(
		name: String? = null,
		username: String? = null,
		bio: String? = null,
		imageUrl: String? = null
	) {
		val uid = auth.currentUser?.uid
		val userData = UserData(
			userId = uid,
			name = name ?: userData.value?.name,
			username = username ?: userData.value?.username,
			bio = bio ?: userData.value?.bio,
			imageUrl = imageUrl ?: userData.value?.imageUrl,
			following = userData.value?.following
		)
		
		uid?.let { it ->
			isLoading.value = true
			db.collection(USERS).document(it).get().addOnSuccessListener { document ->
				if (document.exists()) {
					document.reference.update(userData.toMap()).addOnSuccessListener {
						this.userData.value = userData
						isLoading.value = false
					}.addOnFailureListener { exception ->
						handleException(exception, "Can't update user.")
						isLoading.value = false
					}
				} else {
					db.collection(USERS).document(it).set(userData).addOnFailureListener {
						handleException(
							exception = it, customMessage = "Can't create user."
						)
						isLoading.value = false
					}
					getUserData(it)
					isLoading.value = false
					signedIn.value = true
				}
			}.addOnFailureListener { exception ->
				handleException(exception = exception, customMessage = "Can't create user.")
				isLoading.value = false
			}
		}
	}
	
	private fun getUserData(uid: String) {
		isLoading.value = true
		db.collection(USERS).document(uid).get().addOnSuccessListener { document ->
			val userData = document.toObject<UserData>()
			this.userData.value = userData
			isLoading.value = false
		}.addOnFailureListener { exception ->
			handleException(exception = exception, customMessage = "Can't retrieve user data.")
			isLoading.value = false
		}
	}
	
	fun handleException(exception: Exception? = null, customMessage: String = "") {
		exception?.printStackTrace()
		val errorMessage = exception?.localizedMessage ?: ""
		val message = if (customMessage.isEmpty()) errorMessage else "$customMessage: $errorMessage"
		popUpNotification.value = Event(message)
	}
	
	fun onLogout() {
		auth.signOut()
		signedIn.value = false
	}
}