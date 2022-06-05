package com.task.foodrecipe.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.foodrecipe.data.dtos.responsedtos.RecipeData
import com.task.foodrecipe.data.remote.baseclient.ApiResponse
import com.task.foodrecipe.domain.IDataRepository
import com.task.foodrecipe.ui.dashaboard.adapter.RecipeListAdapter
import com.task.foodrecipe.utils.base.BaseViewModel
import com.task.foodrecipe.utils.base.sealed.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val dataRepository: IDataRepository
) : BaseViewModel(), IDetailVM {

    private val _uiState: MutableLiveData<UIEvent> = MutableLiveData()
    override val uiState: LiveData<UIEvent> = _uiState

    private val _recipeLists: MutableLiveData<List<RecipeData>?> = MutableLiveData()
    override val recipeLists: LiveData<List<RecipeData>?> = _recipeLists

    private val _recipeData: MutableLiveData<RecipeData?> = MutableLiveData()
    override val recipeData: LiveData<RecipeData?> = _recipeData

    override val adaptor: RecipeListAdapter = RecipeListAdapter(mutableListOf())

    override fun setRecipe(recipeData: RecipeData?) {
        _recipeData.value = recipeData
    }

    override fun fetchFreshData() {
        getSimilarRecipes(recipeId = recipeData.value?.id ?: 0)
    }

    override fun getSimilarRecipes(recipeId: Int) {
        launch {
            _uiState.postValue(UIEvent.Loading)
            val response = dataRepository.getSimilarRecipes(recipeId)
            withContext(Dispatchers.Main) {
                when (response) {
                    is ApiResponse.Success -> {
                        _recipeLists.value = if ((response.data.results?.size ?: 0) > 10)
                            response.data.results?.subList(0, 10)
                        else response.data.results
                        _uiState.value = UIEvent.Success
                    }
                    is ApiResponse.Error -> {
                        _recipeLists.value = null
                        _uiState.value = UIEvent.Error(response.error.message)
                    }
                }
            }
        }
    }


}