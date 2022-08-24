package com.example.dependencyinjection.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dependencyinjection.activity.MainActivity
import com.example.dependencyinjection.databinding.ItemPostListBinding
import com.example.dependencyinjection.model.Post
import com.example.dependencyinjection.utils.Utils
import com.example.dependencyinjection.utils.Utils.toast

class PostAdapter(var activity: MainActivity, var items: ArrayList<Post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder(ItemPostListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = items[position]
        if (holder is PostViewHolder) {
            holder.binding.apply {
                tvTitle.text = post.title
                tvBold.text = post.body
                tvTitle.setTypeface(tvTitle.typeface, Typeface.BOLD_ITALIC)

            }
        }
    }

    fun deletePostDialog(post: Post) {
        val title = "Delete"
        val body = "Do you want to delete?"

        Utils.customDialog(activity, title, body, object : Utils.DialogListener {
            override fun onPositiveClick() {
                activity.viewModel.apiPostDelete(post)
                activity.toast("Post which id is: ${post.id} has been deleted!")
                activity.viewModel.deletedPost.observe(activity, {
                    activity.viewModel.apiGetPostsList()
                })
            }

            override fun onNegativeClick() {
                activity.toast("Deleting post failed!")
            }

        })
    }

    override fun getItemCount(): Int = items.size

    class PostViewHolder(var binding: ItemPostListBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}