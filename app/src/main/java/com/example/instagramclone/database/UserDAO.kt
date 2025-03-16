package com.example.instagramclone.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.instagramclone.model.User
import kotlinx.coroutines.flow.Flow

/**
 * UserDAO
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/15/2025.
 *
 **/
 
@Dao
interface UserDAO {
	@Insert(onConflict = OnConflictStrategy.ABORT)
	suspend fun insert(user: User): Long
	
	@Update
	suspend fun update(user: User): Int
	
	@Query("SELECT * FROM users WHERE id = :id")
	suspend fun getUserById(id: Int): User?
	
	@Query("SELECT * FROM users WHERE email = :email AND password = :password")
	suspend fun getUserByEmailAndPassword(email: String, password: String): User?
}