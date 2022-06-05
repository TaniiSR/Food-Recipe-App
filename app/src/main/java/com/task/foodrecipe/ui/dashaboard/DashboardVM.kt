package com.task.foodrecipe.ui.dashaboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.foodrecipe.data.dtos.responsedtos.RecipeData
import com.task.foodrecipe.data.remote.baseclient.ApiResponse
import com.task.foodrecipe.domain.IDataRepository
import com.task.foodrecipe.ui.dashaboard.adapter.RecipeListAdapter
import com.task.foodrecipe.utils.base.BaseViewModel
import com.task.foodrecipe.utils.base.sealed.UIEvent
import com.task.foodrecipe.utils.uikit.pagination.PaginatedRecyclerView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
    private val dataRepository: IDataRepository
) : BaseViewModel(), IDashboardVM {
    private val _uiState: MutableLiveData<UIEvent> = MutableLiveData()
    override val uiState: LiveData<UIEvent> = _uiState

    private val _recipeLists: MutableLiveData<List<RecipeData>> = MutableLiveData()
    override val recipeLists: LiveData<List<RecipeData>> = _recipeLists

    private val _totalRecipeLists: MutableLiveData<List<RecipeData>> = MutableLiveData()
    override val totalRecipeLists: LiveData<List<RecipeData>> = _totalRecipeLists

    private val _isRecipeSuccess: MutableLiveData<Boolean> = MutableLiveData()
    override val isRecipeSuccess: LiveData<Boolean> = _isRecipeSuccess

    private val _isRecipeCompleted: MutableLiveData<Boolean> = MutableLiveData()
    override val isRecipeCompleted: LiveData<Boolean> = _isRecipeCompleted

    override var query: String? = null
    override val adaptor: RecipeListAdapter = RecipeListAdapter(mutableListOf())

    private var job: Job? = null

    override var mPageNumber: Int = 0
    override val mPageSize: Int = 10
    override var isFirstTimeFetching: Boolean = true


    override fun getRecipes(query: String?, pageNo: Int, pageSize: Int) {
        job = launch {
            if (isFirstTimeFetching)
                _uiState.postValue(UIEvent.Loading)
            val response = dataRepository.getRecipes(query = query, page = pageNo, size = pageSize)
            withContext(Dispatchers.Main) {
                when (response) {
                    is ApiResponse.Success -> {
                        _recipeLists.value = response.data.results ?: listOf()
                        isFirstTimeFetching = false
                        _isRecipeSuccess.value = true
                        _isRecipeCompleted.value =
                            (response.data.results?.size == 0 || (response.data.results?.size
                                ?: 0) < mPageSize)
                        _uiState.value = UIEvent.Success
                    }
                    is ApiResponse.Error -> {
                        _isRecipeSuccess.value = false
                        _recipeLists.value = listOf()
                        _uiState.value = UIEvent.Error(response.error.message)
                    }
                }
            }
        }
    }

    private val paginationRecipeListener = object : PaginatedRecyclerView.Pagination() {
        override fun onNextPage(page: Int) {
            mPageNumber = page
            getRecipes(query = query, pageNo = mPageNumber, pageSize = mPageSize)
        }
    }

    override fun getPaginationRecipeListener(): PaginatedRecyclerView.Pagination =
        paginationRecipeListener

    override fun updateTotalRecipeList(list: List<RecipeData>) {
        val totalList: ArrayList<RecipeData> = arrayListOf()
        totalList.addAll(_totalRecipeLists.value ?: arrayListOf())
        totalList.addAll(list)
        _totalRecipeLists.value = totalList
    }

    override fun cancelJob() {
        job?.cancel()
    }

    override fun clearList() {
        _totalRecipeLists.value = listOf()
    }
}
