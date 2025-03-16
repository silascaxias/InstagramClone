package com.example.instagramclone.di

import com.example.instagramclone.database.InstagramCloneDatabase
import com.example.instagramclone.database.StoreManager
import com.example.instagramclone.repository.UserRepository
import com.example.instagramclone.viewmodel.InstagramViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
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
	viewModelOf(::InstagramViewModel)
}