package br.com.gustavopmsilva.topredditsreader.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gustavopmsilva.topredditsreader.data.domain.Post
import br.com.gustavopmsilva.topredditsreader.databinding.PostRowBinding
import coil.load

class PostListAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<PostListAdapter.Holder>() {

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
        return Holder(rowBinding, onClickListener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    class Holder(
        private val binding: PostRowBinding,
        private val onClickListener: OnClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.llLayout.setOnClickListener { onClickListener.onClick(post) }
            binding.tvTitle.text = post.title
            binding.imgThumbnail.load(post.thumbnail)
        }
    }

    class OnClickListener(val clickListener: (post: Post) -> Unit) {
        fun onClick(post: Post) = clickListener(post)
    }
}
