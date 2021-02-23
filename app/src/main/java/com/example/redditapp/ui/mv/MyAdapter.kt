package com.example.redditapp.ui.mv

import android.content.Context
import android.graphics.Color
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.redditapp.R
import com.squareup.picasso.Picasso
import java.io.File

class MyAdapter(list: MutableList<PostData>, context: Context):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var listArrayR=list
    var contextR=context

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val itemTitle=view.findViewById<TextView>(R.id.tvTitle)
        val itemAuthor=view.findViewById<TextView>(R.id.tvAuthor)
        val itemComments=view.findViewById<TextView>(R.id.tvNumComments)
        val itemImage =view.findViewById<ImageView>(R.id.rcImage)

        fun bind(listItem: PostData, context: Context){
            itemTitle.text=listItem.title
            itemAuthor.text=listItem.author
            itemComments.text=listItem.comment_num.toString()
            if(listItem.image.trimMargin()!="self")
            {
                Picasso.get().load(listItem.image).into(itemImage)
                itemImage.setBackgroundColor(Color.RED)
            }
            else
            {
                itemImage.setBackgroundColor(Color.WHITE)
            }
            var downloaded:Long=0
            itemView.setOnClickListener(){
                Toast.makeText(context, "Pressed: ${itemTitle.text}", Toast.LENGTH_SHORT).show()

                if(listItem.image.trimMargin()!="self") {
                   var down = fileManager()
                    if (Utility.checkPermission(context))
                    {
                        down.downloadPicasso(context,Utility.IMAGE_URL, itemImage as AppCompatImageView, File(Environment.DIRECTORY_PICTURES))
                    }

                }
                }
              /*
                {
                var request =DownloadManager.Request(Uri.parse(listItem.image))
                        .setTitle(itemTitle.text)
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                        .setAllowedOverMetered(true)
                    var dm:DownloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    downloaded = dm.enqueue(request)

            }


                if(listItem.image.trimMargin()!="self") {
                    var request = DownloadManager.Request(Uri.parse(listItem.image));

// only download via WIFI
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
                    request.setTitle("Example");
                   // request.setDescription("Downloading a very large zip");

// we just want to download silently

                    //request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
                    //request.setDestinationInExternalFilesDir(context, null, "large.zip");

// enqueue this request
                    var downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager;
                    var downloadID = downloadManager.enqueue(request);
                }
                */
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var inflater = LayoutInflater.from(contextR)
        return MyViewHolder(inflater.inflate(R.layout.rvitem, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var listItem=listArrayR.get(position)
        holder.bind(listItem, contextR)
        //listArrayR.map { holder.bind(it,contextR)}
    }

    override fun getItemCount(): Int {
        return listArrayR.size
    }
}
