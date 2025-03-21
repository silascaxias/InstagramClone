package com.example.instagramclone.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import java.io.File
import java.io.FileOutputStream

/**
 * copyUriToInternalStorage
 * InstagramClone
 *
 ** Created by Silas S. Caxias on 3/15/2025.
 *
 **/

class Utils {
	companion object  {
		fun copyUriToInternalStorage(context: Context, uri: Uri, fileName: String): String? {
			val file = File(context.filesDir, fileName)
			return try {
				context.contentResolver.openInputStream(uri)?.use { inputStream ->
					FileOutputStream(file).use { outputStream ->
						inputStream.copyTo(outputStream)
					}
				}
				file.absolutePath
			} catch (e: Exception) {
				e.printStackTrace()
				null
			}
		}

		fun shareLink(context: Context, link: String) {
			val intent = Intent(Intent.ACTION_SEND).apply {
				type = "text/plain"
				putExtra(Intent.EXTRA_TEXT, link)
			}

			val shareIntent = Intent.createChooser(intent, "Share Link")
			if (intent.resolveActivity(context.packageManager) != null) {
				context.startActivity(shareIntent)
			} else {
				Toast.makeText(context, "No app found to share the link.", Toast.LENGTH_SHORT).show()
			}
		}

		fun shareImage(context: Context, imageFile: File?) {
			if (imageFile == null || !imageFile.exists()) {
				Toast.makeText(context, "Invalid image URI.", Toast.LENGTH_SHORT).show()
				return
			}

			val imageUri: Uri = FileProvider.getUriForFile(
				context,
				"${context.packageName}.fileprovider",
				imageFile
			)

			val intent = Intent(Intent.ACTION_SEND).apply {
				type = "image/*"
				putExtra(Intent.EXTRA_STREAM, imageUri)
				addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
			}

			val shareIntent = Intent.createChooser(intent, "Share Image")
			if (intent.resolveActivity(context.packageManager) != null) {
				context.startActivity(shareIntent)
			} else {
				Toast.makeText(context, "No app found to share the image.", Toast.LENGTH_SHORT).show()
			}
		}
	}
}
