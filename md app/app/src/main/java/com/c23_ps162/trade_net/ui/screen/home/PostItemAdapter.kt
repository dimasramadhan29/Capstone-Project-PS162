package com.c23_ps162.trade_net.ui.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.c23_ps162.trade_net.data.model.PostDisplay
import com.c23_ps162.trade_net.databinding.ItemPostBinding

class PostItemAdapter(
    private val onCardClicked: (PostDisplay) -> Unit,
    private val onLikeClicked: (PostDisplay) -> Unit,
    private val onCommentClicked: (PostDisplay) -> Unit
) : ListAdapter<PostDisplay, PostItemViewHolder>(PostItemDiffer) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        return PostItemViewHolder(
            binding = ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onCardClicked,
            onLikeClicked,
            onCommentClicked
        )
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        val post = currentList[position]
        holder.onBind(post)
    }
}

class PostItemViewHolder(
    private val binding: ItemPostBinding,
    private val onCardClicked: (PostDisplay) -> Unit,
    private val onLikeClicked: (PostDisplay) -> Unit,
    private val onCommentClicked: (PostDisplay) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: PostDisplay) {
        binding.root.setOnClickListener { onCardClicked.invoke(data) }
        binding.likeButton.setOnClickListener { onLikeClicked.invoke(data) }
        binding.commentButton.setOnClickListener { onCommentClicked.invoke(data) }
    }
}

object PostItemDiffer : DiffUtil.ItemCallback<PostDisplay>() {
    override fun areItemsTheSame(oldItem: PostDisplay, newItem: PostDisplay): Boolean {
        return oldItem.post.content?.contentId == newItem.post.content?.contentId
    }

    override fun areContentsTheSame(oldItem: PostDisplay, newItem: PostDisplay): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}