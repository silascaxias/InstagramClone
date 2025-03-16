package com.example.instagramclone.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.instagramclone.data.model.User

/**
 * UserDAO
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/15/2025.
 *
 **/
 
@Dao
interface UserDAO {
	@Insert(onConflict = OnConflictStrategy.Companion.ABORT)
	suspend fun insert(user: User): Long
	
	@Update(onConflict = OnConflictStrategy.Companion.ABORT)
	suspend fun update(user: User): Int
	
	@Query("SELECT * FROM users WHERE id = :id")
	suspend fun getUserById(id: Int): User?
	
	@Query("SELECT * FROM users WHERE email = :email AND password = :password")
	suspend fun getUserByEmailAndPassword(email: String, password: String): User?
}