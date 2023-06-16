package com.c23_ps162.trade_net.ui.screen.interest

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.c23_ps162.trade_net.R
import com.c23_ps162.trade_net.databinding.ItemInterestBinding
import com.c23_ps162.trade_net.ui.model.InterestDisplay

class InterestListAdapter(
    private val onCLick: (InterestDisplay) -> Unit
) : ListAdapter<InterestDisplay, InterestViewHolder>(InterestDiffer) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InterestViewHolder {
        return InterestViewHolder(
            binding = ItemInterestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onCLick = onCLick
        )
    }

    override fun onBindViewHolder(
        holder: InterestViewHolder,
        position: Int
    ) {
        val data = currentList[position]
        holder.bind(data)
    }
}

class InterestViewHolder(
    private val binding: ItemInterestBinding,
    private val onCLick: (InterestDisplay) -> Unit
) : ViewHolder(binding.root) {
    fun bind(data: InterestDisplay) {
        /** image **/
        Glide.with(binding.img)
            .load(data.parent.imageUrl)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(binding.img)

        /** name **/
        binding.name.text = data.parent.name

        /** selection **/
        binding.root.setStrokeColor(
            ColorStateList.valueOf(
                binding.root.context.resources.getColor(
                    if (data.selected)
                        R.color.weird_orange
                    else
                        R.color.grey_neutral
                )
            )
        )

        /** action click **/
        binding.root.setOnClickListener {
            onCLick.invoke(data)
        }
    }
}

object InterestDiffer : DiffUtil.ItemCallback<InterestDisplay>() {
    override fun areItemsTheSame(
        oldItem: InterestDisplay,
        newItem: InterestDisplay
    ): Boolean {
        return oldItem.parent.id == newItem.parent.id
    }

    override fun areContentsTheSame(
        oldItem: InterestDisplay,
        newItem: InterestDisplay
    ): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}