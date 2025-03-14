package com.example.instagramclone

import android.app.Application
import com.example.instagramclone.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * InstagramCloneApplication
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

class InstagramCloneApplication: Application() {
	override fun onCreate() {
		super.onCreate()
		
		startKoin {
			androidLogger()
			androidContext(this@InstagramCloneApplication)
			modules(appModule)
		}
	}
}