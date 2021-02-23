package com.example.redditapp.ui.mv

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import com.squareup.picasso.Picasso
import java.io.File
import java.lang.Exception

class fileManager() {
    fun downloadPicasso(context: Context, url: String?, imageView: AppCompatImageView, tvImagePath: File) {
        Picasso.get()
                .load(url)
                .into(object : com.squareup.picasso.Target {
                    override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                        Utility.saveImage(context, bitmap, imageView, tvImagePath)
                       // progressBar.visibility = View.GONE
                    }



                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                        //progressBar.visibility = View.GONE
                        Utility.showToast(context, "Bitmap failed to load")
                    }

                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                        imageView.setImageDrawable(placeHolderDrawable)
                        //progressBar.visibility = View.GONE
                    }
                })
    }
    private fun downloadImage(url: String) {

        // ...
/*
        val downloadManager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val downloadUri = Uri.parse(url)

        val request = DownloadManager.Request(downloadUri).apply {
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle(url.substring(url.lastIndexOf("/") + 1))
                    .setDescription("")
                    .setDestinationInExternalPublicDir(
                            directory.toString(),
                            url.substring(url.lastIndexOf("/") + 1)
                    )
        }

        // ...
    }

 */
}
}