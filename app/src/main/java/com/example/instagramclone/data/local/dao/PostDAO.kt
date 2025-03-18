package com.example.instagramclone.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.instagramclone.data.entity.Post
import com.example.instagramclone.data.entity.PostWithUser
import kotlinx.coroutines.flow.Flow

/**
 * PostDAO
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/18/2025.
 *
 **/

@Dao
interface PostDAO {
	@Insert(onConflict = OnConflictStrategy.Companion.ABORT)
	suspend fun insert(post: Post): Long
	
	@Transaction
	@Query("SELECT * FROM posts WHERE userId = :userId")
	fun getUserPosts(userId: Int): Flow<List<PostWithUser>>
	
	@Transaction
	@Query("SELECT * FROM posts")
	fun getAllPosts(): Flow<List<PostWithUser>>
	
	@Query("DELETE FROM posts")
	suspend fun deleteAllPosts(): Int
}
