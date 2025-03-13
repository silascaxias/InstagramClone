package com.example.instagramclone.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * HiltModule
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
	@Provides
	fun provideAuth(): FirebaseAuth = Firebase.auth
	
	@Provides
	fun proveFireStore(): FirebaseFirestore = Firebase.firestore
	
	@Provides
	fun provideStorage(): FirebaseStorage = Firebase.storage
}