package com.example.instagramclone.di

import com.example.instagramclone.data.local.InstagramCloneDatabase
import com.example.instagramclone.data.local.StoreManager
import com.example.instagramclone.data.repository.PostRepository
import com.example.instagramclone.data.repository.UserRepository
import com.example.instagramclone.viewmodel.InstagramViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
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
	single { InstagramCloneDatabase(androidContext()) }
	single { StoreManager(androidContext()) }
	singleOf(::UserRepository)
	singleOf(::PostRepository)
	viewModelOf(::InstagramViewModel)
}