package br.com.gustavopmsilva.topredditsreader.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gustavopmsilva.topredditsreader.data.model.Post
import br.com.gustavopmsilva.topredditsreader.databinding.PostRowBinding
import coil.load

class PostAdapter : RecyclerView.Adapter<PostAdapter.Holder>() {

    var posts: List<Post> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val rowBinding = PostRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(rowBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    class Holder(private val binding: PostRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.imgThumbnail.load(post.data.thumbnail)
        }
    }
}