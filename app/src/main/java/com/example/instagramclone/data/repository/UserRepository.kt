package com.example.instagramclone.data.repository

import com.example.instagramclone.data.local.InstagramCloneDatabase
import com.example.instagramclone.data.local.StoreManager
import com.example.instagramclone.data.model.User

/**
 * UserRepository
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/15/2025.
 *
 **/

class UserRepository(
	private var database: InstagramCloneDatabase,
	private var storeManager: StoreManager
) {
	
	suspend fun getUserById(id: Int): User? = database.userDAO().getUserById(id)
	
	suspend fun getUserByEmailAndPassword(email: String, password: String) = database.userDAO().getUserByEmailAndPassword(email, password)
	
	suspend fun insert(user: User): Long = database.userDAO().insert(user)
	
	suspend fun update(user: User) = database.userDAO().update(user)
	
	suspend fun getUserLoggedInId() = storeManager.getUserLoggedInId()
	
	suspend fun onLogin(userId: Int) = storeManager.saveUserId(userId)
	
	suspend fun onLogout() = storeManager.saveUserId(null)
	
	fun onDestroy() = database.close()
}