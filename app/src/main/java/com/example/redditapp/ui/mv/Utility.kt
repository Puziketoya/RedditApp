package com.example.redditapp.ui.mv


import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.redditapp.R
import java.io.File
import java.io.FileOutputStream
import java.util.*

/**
 * Created by Govind on 2/23/2018.
 */
object Utility {
    private const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123

    var IMAGE_URL = "https://raw.githubusercontent.com/AndroidCodility/Picasso-RecyclerView/master/images/marshmallow.png"
    var VOLLEY_IMAGE_URL = "https://raw.githubusercontent.com/AndroidCodility/StaggeredRecyclerView/master/images/eight.jpg"

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun checkPermission(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    val alertBuilder = AlertDialog.Builder(context)
                    alertBuilder.setCancelable(true)
                    alertBuilder.setTitle(R.string.permission_necessary)
                    alertBuilder.setMessage(R.string.external_storage_permission)
                    alertBuilder.setPositiveButton(android.R.string.yes) { dialog, which -> ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) }
                    val alert = alertBuilder.create()
                    alert.show()
                } else {
                    ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun saveImage(context: Context, bitmap: Bitmap, imageView: AppCompatImageView, tvImagePath: File) {
        try {
            val root = Environment.getExternalStorageDirectory().toString()
            var myDir = File(root + "/Codility Pictures")

            if (!myDir.exists()) {
                myDir.mkdirs()
            }

            val name = Date().toString() + ".jpg"
            myDir = File(myDir, name)
            val out = FileOutputStream(myDir)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
            showToast(context, "Downloaded Successfully..!!")
            imageView.setImageBitmap(bitmap)

        } catch (e: Exception) {
            Log.e("Exception ", e.message.toString())
        }
    }

    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}