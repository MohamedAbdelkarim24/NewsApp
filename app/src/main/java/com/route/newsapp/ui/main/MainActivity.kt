package com.route.newsapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.route.newsapp.R
import com.route.newsapp.api.ApiManager
import com.route.newsapp.api.model.SourcesResponse
import com.route.newsapp.databinding.ActivityMainBinding
import com.route.newsapp.ui.category.CategoryFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,CategoryFragment())
            .commit()
//        ApiManager.getApis().getResorces(apiKey)
//            .enqueue(object :Callback<SourcesResponse>{
//            override fun onResponse(
//                call: Call<SourcesResponse>,
//                response: Response<SourcesResponse>
//            ) {
//
//            }
//
//            override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//
//            }
//        })
    }
}