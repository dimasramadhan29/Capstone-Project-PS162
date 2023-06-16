package com.c23_ps162.trade_net.ui.screen.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.c23_ps162.trade_net.data.model.PostDisplay
import com.c23_ps162.trade_net.databinding.ItemPostBinding
import com.c23_ps162.trade_net.databinding.ItemSearchPostBinding

class SearchItemAdapter(
    private val onCardClicked: (PostDisplay) -> Unit,
) : ListAdapter<PostDisplay, SearchPostItemViewHolder>(PostItemDiffer) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPostItemViewHolder {
        return SearchPostItemViewHolder(
            binding = ItemSearchPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onCardClicked,
        )
    }

    override fun onBindViewHolder(holder: SearchPostItemViewHolder, position: Int) {
        val post = currentList[position]
        holder.onBind(post)
    }
}

class SearchPostItemViewHolder(
    private val binding: ItemSearchPostBinding,
    private val onCardClicked: (PostDisplay) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: PostDisplay) {
        binding.root.setOnClickListener { onCardClicked.invoke(data) }
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