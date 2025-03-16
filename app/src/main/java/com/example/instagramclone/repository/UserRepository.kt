package com.example.instagramclone.repository

import android.database.sqlite.SQLiteConstraintException
import com.example.instagramclone.database.InstagramCloneDatabase
import com.example.instagramclone.database.StoreManager
import com.example.instagramclone.model.User
import java.io.IOException

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
	
	suspend fun getUserByEmailAndPassword(email: String, password: String): User? =
		database.userDAO().getUserByEmailAndPassword(email, password)
	
	suspend fun insert(user: User): Long {
		return database.userDAO().insert(user)
	}
	
	suspend fun update(user: User) = database.userDAO().update(user)
	
	suspend fun getUserLoggedInId() = storeManager.getUserLoggedInId()
	
	suspend fun onLogin(userId: Int) = storeManager.saveUserId(userId)
	
	suspend fun onLogout() = storeManager.saveUserId(null)
}