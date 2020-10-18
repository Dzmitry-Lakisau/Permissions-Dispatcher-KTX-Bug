package com.intexsoft.myapplication

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.ktx.constructPermissionsRequest

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val cameraPermissionRequest = constructPermissionsRequest(Manifest.permission.CAMERA) {
			openCamera()
		}
		val storagePermissionRequest = constructPermissionsRequest(Manifest.permission.READ_EXTERNAL_STORAGE) {
			openFileManager()
		}

		button_camera.setOnClickListener {
			cameraPermissionRequest.launch()
		}
		button_file_manager.setOnClickListener {
			storagePermissionRequest.launch()
		}
	}

	private fun openCamera() {
		val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Environment.DIRECTORY_DOWNLOADS + "/temp.png")
		startActivityForResult(intent, 1)
	}

	private fun openFileManager() {
		val intent = Intent().setAction(Intent.ACTION_GET_CONTENT)
		startActivityForResult(Intent.createChooser(intent, "Choose file"), 2)
	}
}
