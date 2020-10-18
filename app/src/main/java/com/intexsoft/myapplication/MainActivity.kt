package com.intexsoft.myapplication

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		button_camera.setOnClickListener {
			openCameraWithPermissionCheck()
		}
		button_file_manager.setOnClickListener {
			openFileManagerWithPermissionCheck()
		}
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		onRequestPermissionsResult(requestCode, grantResults)
	}

	@NeedsPermission(Manifest.permission.CAMERA)
	fun openCamera() {
		val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Environment.DIRECTORY_DOWNLOADS + "/temp.png")
		startActivityForResult(intent, 1)
	}

	@NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
	fun openFileManager() {
		val intent = Intent().setAction(Intent.ACTION_GET_CONTENT)
		startActivityForResult(Intent.createChooser(intent, "Choose file"), 2)
	}
}
