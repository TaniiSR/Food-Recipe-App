package com.task.foodrecipe.ui.dashaboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.task.foodrecipe.R
import com.task.foodrecipe.data.dtos.responsedtos.RecipeData
import com.task.foodrecipe.databinding.LayoutItemRecipeListViewBinding
import com.task.foodrecipe.utils.base.BaseRecyclerAdapter

class RecipeListAdapter(
    private val list: MutableList<RecipeData>,
) : BaseRecyclerAdapter<RecipeData, RecipeListItemViewHolder>(list) {
    override fun onCreateViewHolder(binding: ViewBinding): RecipeListItemViewHolder {
        return RecipeListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeListItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position, onItemClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_item_recipe_list_view
    }

    override fun getItemCount(): Int = list.size

    override fun getViewBindingByViewType(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return LayoutItemRecipeListViewBinding.inflate(layoutInflater, viewGroup, false)
    }

    fun updateRecipeListItems(recipeList: List<RecipeData>) {
        val diffCallback = RecipeDiffCallback(list, recipeList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        this.setList(recipeList)
        diffResult.dispatchUpdatesTo(this)
    }
}