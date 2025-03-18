package com.example.instagramclone.data.repository

import com.example.instagramclone.data.entity.Post
import com.example.instagramclone.data.entity.PostWithUser
import com.example.instagramclone.data.local.InstagramCloneDatabase
import kotlinx.coroutines.flow.Flow

/**
 * PostRepository
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/18/2025.
 *
 **/
class PostRepository(
	private var database: InstagramCloneDatabase
) {
	
	val allPosts: Flow<List<PostWithUser>> = database.postDAO().getAllPosts()
	
	fun userPosts(userId: Int): Flow<List<PostWithUser>> = database.postDAO().getUserPosts(userId = userId)
	
	suspend fun insert(post: Post) = database.postDAO().insert(post)
	
	suspend fun deleteAllPosts() = database.postDAO().deleteAllPosts()
}