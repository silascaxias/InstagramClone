package com.example.instagramclone.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.instagramclone.model.User

/**
 * instagramCloneDatabase
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/15/2025.
 *
 **/

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class InstagramCloneDatabase : RoomDatabase() {
	abstract fun userDAO(): UserDAO
	
	companion object {
		@Volatile
		private var instance: InstagramCloneDatabase? = null
		
		operator fun invoke(context: Context) = instance ?: synchronized(this) {
			instance ?: createDatabase(context).also {
				instance = it
			}
		}
		
		private fun createDatabase(context: Context) = Room.databaseBuilder(
			context.applicationContext,
			InstagramCloneDatabase::class.java,
			"instagram_clone_database"
		).build()
	}
}