package com.route.newsapp.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import com.route.newsapp.api.ApiConstants
import com.route.newsapp.api.ApiManager
import com.route.newsapp.api.model.SourcesItem
import com.route.newsapp.api.model.SourcesResponse
import com.route.newsapp.databinding.FragmentCategoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFragment:Fragment() {
    lateinit var viewBinding: FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding=FragmentCategoryBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadNews()
    }

    private fun loadNews() {
        showLoadingLayout()
        ApiManager
            .getApis()
            .getResorces(ApiConstants.apiKey)
            .enqueue(object:Callback<SourcesResponse>{
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    viewBinding.loadingIndicator.isVisible=false
                    if (response.isSuccessful){
                        bindSourcesInTabLayout(response.body()?.sources)
                    }else{
                        //errorBody type of string this is json we should convert it to object of sourcesResponse
                        //we will use fun fromjson
                        val gson=Gson()
                       val errorResponse= gson.fromJson(response.errorBody()?.string(),SourcesResponse::class.java)
                        showErrorMessage(errorResponse.message)
                    }


                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    viewBinding.loadingIndicator.visibility=View.GONE
                    showErrorMessage(t.localizedMessage)

                }

            })
    }

    private fun showLoadingLayout() {
        viewBinding.loadingIndicator.isVisible=true
        viewBinding.errorLayout.isVisible=false
    }

    private fun showErrorMessage(message: String?) {
        viewBinding.errorLayout.isVisible=true
        viewBinding.loadingIndicator.isVisible=false
        viewBinding.errorMessage.text = message
    }

    fun bindSourcesInTabLayout(sourcesList:List<SourcesItem?>?){
        sourcesList?.forEach{
           val tab= viewBinding.tabLayout.newTab()
            tab.text=it?.name
            tab.setTag(it)
            viewBinding.tabLayout.addTab(tab)
        }
        viewBinding.tabLayout.addOnTabSelectedListener(object :OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val source=tab?.tag as SourcesItem
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

        })
    }

}