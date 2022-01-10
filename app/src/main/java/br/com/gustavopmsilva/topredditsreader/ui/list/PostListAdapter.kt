package br.com.gustavopmsilva.topredditsreader.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.gustavopmsilva.topredditsreader.data.domain.Post
import br.com.gustavopmsilva.topredditsreader.databinding.PostRowBinding
import coil.load

class PostListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Post, PostListAdapter.Holder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val rowBinding = PostRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(rowBinding, onClickListener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class Holder(
        private val binding: PostRowBinding,
        private val onClickListener: OnClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.imgThumbnail.apply {
                setOnClickListener { onClickListener.onClick(post) }
                load(post.thumbnail)
            }
        }
    }

    class OnClickListener(val clickListener: (post: Post) -> Unit) {
        fun onClick(post: Post) = clickListener(post)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

    }
}
