package com.example.redditapp.ui.mv
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redditapp.ui.data.RedditApiService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.*
import java.io.File

class MainViewModel:ViewModel() {

    var liveData = MutableLiveData<MutableList<PostData>>()
    val apiService = RedditApiService()


    init {
        GlobalScope.async {
            update()
        }

    }

     suspend fun update() {

         val posts = apiService.getPosts(20).await()

         var list:MutableList<PostData> = mutableListOf()
        for (i in posts.data.children)
        {
            list.add(PostData( i.data.title, i.data.author, i.data.num_comments, i.data.thumbnail))
        }
        liveData.value=list
    }


}