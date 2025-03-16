package com.example.instagramclone.data.model

/**
 * Event
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/13/2025.
 *
 **/

open class Event<out T>(private val content: T) {
	var hasBeenHandled = false
		private set
	
	fun getContentOrNull(): T? {
		return if (hasBeenHandled) {
			null
		} else {
			hasBeenHandled = true
			return content
		}
	}
}