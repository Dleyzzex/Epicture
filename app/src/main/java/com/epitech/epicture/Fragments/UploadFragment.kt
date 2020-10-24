package com.epitech.epicture

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


import java.io.ByteArrayOutputStream
import androidx.appcompat.app.AppCompatActivity
import com.epitech.epicture.Activities.HomeActivity
import com.epitech.epicture.Activities.UploadActivity
import com.epitech.epicture.R


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UploadFragment(accessToken: String) : Fragment() {

    val _accessToken = accessToken
    var _image : Uri? = null
    private val SELECTED_CODE = 1000
    private val CAMERA_CODE = 1001
    private val REQUEST_CODE = 1002

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_upload, container, false)
        val buttonCamera: Button = root.findViewById(R.id.btnCamera)
        val buttonPick: Button = root.findViewById(R.id.btnPick)
        checkPermission()
        buttonCamera.setOnClickListener {
            openCamera()
        }
        buttonPick.setOnClickListener {
            pickImage()
        }
        return root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun openCamera()
    {
        val values = ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "new title")
        values.put(MediaStore.Images.Media.DESCRIPTION, "new description")
        _image = context?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, _image)
        startActivityForResult(cameraIntent, CAMERA_CODE)
    }

    private fun pickImage()
    {
        val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, SELECTED_CODE)
    }

    private fun checkPermission()
    {
        if (this.context?.checkPermission("WRITE_EXTERNAL_STORAGE", 1, 1) != PERMISSION_GRANTED){
            requestPermissions(arrayOf(WRITE_EXTERNAL_STORAGE), REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val intent = Intent(this.requireContext(), UploadActivity::class.java)
            intent.putExtra("access_token", _accessToken)
            if (requestCode == CAMERA_CODE) {
                intent.apply { putExtra("image", _image.toString()) }
            }
            if (requestCode == SELECTED_CODE){
                //imageView.setImageURI(data?.data) // handle chosen image
                intent.apply { putExtra("image", data?.data.toString()) }
            }
            startActivity(intent)
        }
    }
}