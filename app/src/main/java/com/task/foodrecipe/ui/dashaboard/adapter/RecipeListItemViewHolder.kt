package com.task.foodrecipe.ui.dashaboard.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.task.foodrecipe.R
import com.task.foodrecipe.data.dtos.responsedtos.RecipeData
import com.task.foodrecipe.databinding.LayoutItemRecipeListViewBinding
import com.task.foodrecipe.utils.base.interfaces.OnItemClickListener
import com.task.foodrecipe.utils.extensions.loadImage


class RecipeListItemViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun onBind(
        data: RecipeData,
        position: Int,
        onItemClickListener: OnItemClickListener?
    ) {
        when (binding) {
            is LayoutItemRecipeListViewBinding -> {
                if (data?.videoUrl == null)
                    binding.ivRecipe.loadImage(data?.thumbnailUrl)
                else {
                    binding.ivRecipe.loadImage(R.drawable.ic_play)
                    binding.ivRecipe.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(data?.videoUrl)
                        startActivity(binding.root.context, intent, null)
                    }
                }
                binding.tvName.text = data.name
                binding.tvDescription.text = data.description
            }
        }
    }
}