package com.task.foodrecipe.ui.dashaboard

import android.text.TextWatcher
import androidx.lifecycle.LiveData
import com.task.foodrecipe.data.dtos.responsedtos.RecipeData
import com.task.foodrecipe.ui.dashaboard.adapter.RecipeListAdapter
import com.task.foodrecipe.utils.base.interfaces.IBaseViewModel
import com.task.foodrecipe.utils.base.sealed.UIEvent
import com.task.foodrecipe.utils.uikit.pagination.PaginatedRecyclerView

interface IDashboardVM : IBaseViewModel {
    var isFirstTimeFetching: Boolean
    val uiState: LiveData<UIEvent>
    val recipeLists: LiveData<List<RecipeData>>
    var query: String?
    val adaptor: RecipeListAdapter
    var mPageNumber: Int
    val mPageSize: Int
    val totalRecipeLists: LiveData<List<RecipeData>>
    val isRecipeSuccess: LiveData<Boolean>
    val isRecipeCompleted: LiveData<Boolean>
    fun updateTotalRecipeList(list: List<RecipeData>)
    fun cancelJob()
    fun clearList()
    fun getRecipes(query: String?, pageNo: Int, pageSize: Int)
    fun getPaginationRecipeListener(): PaginatedRecyclerView.Pagination
}