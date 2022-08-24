package com.example.dependencyinjection.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dependencyinjection.activity.MainActivity
import com.example.dependencyinjection.model.Post
import com.example.dependencyinjection.network.service.PostService
import com.example.dependencyinjection.utils.Utils.toast
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class MainViewModel @Inject constructor(val activity: MainActivity, private val postService: PostService): ViewModel() {

    val allPosts = MutableLiveData<ArrayList<Post>>()
    var deletedPost = MutableLiveData<Post>()

    fun apiGetPostsList() {
        postService.listPosts().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>,
            ) {
                allPosts.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {

            }

        })
    }

    fun apiPostDelete(post: Post) {
        postService.deletePost(post.id).enqueue(object : Callback<Post> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                activity.toast("Post which is id:  ${post.id} has been deleted successfully!")
                deletedPost.value = response.body()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                allPosts.value = null
                activity.toast("Deleting post failed!")
            }

        })
    }


}