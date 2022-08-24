package com.example.dependencyinjection.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.dependencyinjection.R
import com.example.dependencyinjection.adapter.PostAdapter
import com.example.dependencyinjection.databinding.ActivityMainBinding
import com.example.dependencyinjection.model.Post
import com.example.dependencyinjection.utils.SampleClass
import com.example.dependencyinjection.viewModel.MainViewModel
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@AndroidEntryPoint
@InstallIn(SingletonComponent::class)
class MainActivity @Inject constructor(): AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var postAdapter: PostAdapter

    val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
//        viewModel = ViewModelProvider(this)[MainViewModel::class.java] // Old style
        viewModel.apiGetPostsList()
        viewModel.allPosts.observe(this, {
            refreshAdapter(it)
        })


    }

    private fun refreshAdapter(items: ArrayList<Post>) {
        postAdapter = PostAdapter(this, items)
        binding.rvMain.adapter = postAdapter
    }
}