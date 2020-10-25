package com.epitech.epicture

import android.Manifest
import android.Manifest.permission.*
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
import androidx.core.content.ContextCompat.checkSelfPermission
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_upload, container, false)
        val buttonPick: Button = root.findViewById(R.id.btnPick)
        checkPermission()
        buttonPick.setOnClickListener {
            pickImage()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun pickImage()
    {
        val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, SELECTED_CODE)
    }

    private fun checkPermission()
    {
        val permissions = arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, CAMERA)
        if (this.context?.checkSelfPermission(WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED || this.context?.checkSelfPermission(READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED ||  this.context?.checkSelfPermission(CAMERA) != PERMISSION_GRANTED)
            requestPermissions(permissions, 666)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val intent = Intent(this.requireContext(), UploadActivity::class.java)
            intent.putExtra("access_token", _accessToken)
            if (requestCode == SELECTED_CODE){
                //imageView.setImageURI(data?.data) // handle chosen image
                intent.apply { putExtra("image", data?.data.toString()) }
            }
            startActivity(intent)
        }
    }
}