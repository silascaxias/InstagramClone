package com.example.instagramclone.di

import com.example.instagramclone.viewmodel.InstagramViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * AppModule
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

val appModule = module {
	single { FirebaseFirestore.getInstance() }
	single { FirebaseAuth.getInstance() }
	single { FirebaseStorage.getInstance() }
	viewModel { InstagramViewModel(get(), get(), get()) }
}