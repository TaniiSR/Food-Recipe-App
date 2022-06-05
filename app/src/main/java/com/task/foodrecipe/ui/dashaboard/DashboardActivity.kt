package com.task.foodrecipe.ui.dashaboard

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import com.task.foodrecipe.R
import com.task.foodrecipe.data.dtos.responsedtos.RecipeData
import com.task.foodrecipe.databinding.ActivityDashboardBinding
import com.task.foodrecipe.ui.detail.DetailActivity
import com.task.foodrecipe.utils.base.BaseActivity
import com.task.foodrecipe.utils.base.interfaces.OnItemClickListener
import com.task.foodrecipe.utils.base.sealed.Dispatcher
import com.task.foodrecipe.utils.base.sealed.UIEvent
import com.task.foodrecipe.utils.extensions.gone
import com.task.foodrecipe.utils.extensions.observe
import com.task.foodrecipe.utils.extensions.visible
import com.task.foodrecipe.utils.uikit.searchview.SearchView
import com.task.foodrecipe.utils.uikit.toolbarview.ToolBarView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding, IDashboardVM>(),
    SearchView.OnSearchViewClickListener, ToolBarView.OnToolBarViewClickListener {
    override val viewModel: IDashboardVM by viewModels<DashboardVM>()
    override fun getViewBinding(): ActivityDashboardBinding =
        ActivityDashboardBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelObservers()
        setListener()
        setUpRecyclerView()
        mViewBinding.searchView.textWatcher = watcher
    }

    private val watcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        override fun afterTextChanged(s: Editable?) {
            if (!s.isNullOrEmpty())
                viewModel.launch(Dispatcher.Main) {
                    delay(600)
                    viewModel.cancelJob()
                    viewModel.query = s.toString()
                    viewModel.clearList()
                    viewModel.isFirstTimeFetching = true
                    mViewBinding.recyclerView.pagination?.notifyPaginationRestart()
                }
        }
    }

    private fun setUpRecyclerView() {
        viewModel.adaptor.onItemClickListener = rvItemClickListener
        viewModel.adaptor.allowFullItemClickListener = true
        mViewBinding.recyclerView.adapter = viewModel.adaptor
        mViewBinding.recyclerView.threshold = 3
        mViewBinding.recyclerView.pagination = viewModel.getPaginationRecipeListener()
        if (viewModel.isFirstTimeFetching)
            mViewBinding.recyclerView.pagination?.notifyPaginationRestart()
    }

    private val rvItemClickListener = object : OnItemClickListener {
        override fun onItemClick(view: View, data: Any, pos: Int) {
            when (data) {
                is RecipeData -> {
                    startDetailScreen(data)
                }
            }
        }
    }

    private fun startDetailScreen(data: RecipeData) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity::class.java.name, data)
        startActivity(intent)
    }

    private fun handleRecipeList(list: List<RecipeData>) {
        viewModel.updateTotalRecipeList(list)
    }

    private fun handleTotalRecipeList(list: List<RecipeData>) {
        viewModel.adaptor.updateRecipeListItems(list)
    }

    private fun setListener() {
        mViewBinding.errorView.btnRetry.setOnClickListener(this)
        mViewBinding.tbView.setOnToolBarViewClickListener(this)
        mViewBinding.searchView.setOnToolBarViewClickListener(this)
        mViewBinding.swRefresh.setOnRefreshListener {
            onCancelClicked()
            viewModel.clearList()
            viewModel.isFirstTimeFetching = true
            mViewBinding.swRefresh.isRefreshing = false
            mViewBinding.recyclerView.pagination?.notifyPaginationRestart()
        }
    }

    override fun onEndIconClicked() {
        mViewBinding.searchView.openSearch()
        mViewBinding.tbView.visibility = View.INVISIBLE
    }

    override fun onCancelClicked() {
        mViewBinding.searchView.closeSearch()
        mViewBinding.tbView.visibility = View.VISIBLE
        viewModel.cancelJob()
        viewModel.query = null
        viewModel.isFirstTimeFetching = true
        mViewBinding.recyclerView.pagination?.notifyPaginationRestart()
    }

    override fun onClick(id: Int) {
        when (id) {
            R.id.btnRetry -> viewModel.getRecipes(
                viewModel.query,
                viewModel.mPageNumber,
                viewModel.mPageSize
            )
        }
    }

    private fun handleUiState(uiEvent: UIEvent) {
        when (uiEvent) {
            is UIEvent.Loading -> setLoadingView()
            is UIEvent.Success -> setSuccessView()
            is UIEvent.Error -> setErrorView()
            is UIEvent.Message -> showToast(uiEvent.message)
        }
    }

    private fun setErrorView() {
        mViewBinding.errorView.errorView.visible()
        mViewBinding.recyclerView.gone()
        hideShimmerLoading()
    }

    private fun setSuccessView() {
        mViewBinding.recyclerView.visible()
        mViewBinding.errorView.errorView.gone()
        hideShimmerLoading()
    }

    private fun setLoadingView() {
        mViewBinding.errorView.errorView.gone()
        mViewBinding.recyclerView.gone()
        showShimmerLoading()
    }

    private fun showShimmerLoading() {
        mViewBinding.shimmerLayout.shimmerFrameLayout.visible()
        mViewBinding.shimmerLayout.shimmerFrameLayout.startShimmer()

    }

    private fun hideShimmerLoading() {
        mViewBinding.shimmerLayout.shimmerFrameLayout.gone()
        mViewBinding.shimmerLayout.shimmerFrameLayout.stopShimmer()
    }

    private fun handleRecipeCompleted(isCompleted: Boolean) {
        if (isCompleted) viewModel.getPaginationRecipeListener().notifyPaginationCompleted()
    }

    private fun handleRecipeSuccess(isSuccess: Boolean) {
        if (isSuccess) viewModel.getPaginationRecipeListener().notifyPageLoaded() else {
            if (viewModel.isFirstTimeFetching) setErrorView()
            else
                viewModel.getPaginationRecipeListener().notifyPageError()
        }
    }

    private fun viewModelObservers() {
        observe(viewModel.recipeLists, ::handleRecipeList)
        observe(viewModel.totalRecipeLists, ::handleTotalRecipeList)
        observe(viewModel.isRecipeCompleted, ::handleRecipeCompleted)
        observe(viewModel.isRecipeSuccess, ::handleRecipeSuccess)
        observe(viewModel.uiState, ::handleUiState)
    }
}
