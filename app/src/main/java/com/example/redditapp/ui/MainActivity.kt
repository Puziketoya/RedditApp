package com.example.redditapp.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditapp.R
import com.example.redditapp.ui.data.RedditApiService
import com.example.redditapp.ui.mv.MainViewModel
import com.example.redditapp.ui.mv.MyAdapter
import com.example.redditapp.ui.mv.PostData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {

    lateinit var mv:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mv = ViewModelProviders.of(this).get(MainViewModel::class.java)
        btRefresh.setOnClickListener(){
            GlobalScope.async(Dispatchers.Main) {
                mv.update()
            }

    }
    }



    override fun onResume() {
        super.onResume()

            mv.liveData.observe(this, Observer {
                Toast.makeText(this,"Hope:", Toast.LENGTH_SHORT).show()
                rvMain.layoutManager = LinearLayoutManager(this)
                rvMain.adapter = MyAdapter(it, this)

            })
        }

   }



