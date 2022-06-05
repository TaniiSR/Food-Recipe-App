package com.task.foodrecipe.ui.detail

import androidx.lifecycle.LiveData
import com.task.foodrecipe.data.dtos.responsedtos.RecipeData
import com.task.foodrecipe.data.dtos.responsedtos.User
import com.task.foodrecipe.ui.dashaboard.adapter.RecipeListAdapter
import com.task.foodrecipe.utils.base.interfaces.IBaseViewModel
import com.task.foodrecipe.utils.base.sealed.UIEvent

interface IDetailVM : IBaseViewModel {
    val uiState: LiveData<UIEvent>
    val recipeData: LiveData<RecipeData?>
    val recipeLists: LiveData<List<RecipeData>?>
    val adaptor: RecipeListAdapter
    fun setRecipe(recipeData: RecipeData?)
    fun fetchFreshData()
    fun getSimilarRecipes(recipeId: Int)
}