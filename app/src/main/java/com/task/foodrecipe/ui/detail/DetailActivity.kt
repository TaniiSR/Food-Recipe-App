package com.task.foodrecipe.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.task.foodrecipe.R
import com.task.foodrecipe.data.dtos.responsedtos.RecipeData
import com.task.foodrecipe.data.dtos.responsedtos.User
import com.task.foodrecipe.databinding.ActivityDetailBinding
import com.task.foodrecipe.utils.base.BaseActivity
import com.task.foodrecipe.utils.base.interfaces.OnItemClickListener
import com.task.foodrecipe.utils.base.sealed.UIEvent
import com.task.foodrecipe.utils.extensions.gone
import com.task.foodrecipe.utils.extensions.loadImage
import com.task.foodrecipe.utils.extensions.observe
import com.task.foodrecipe.utils.extensions.visible
import com.task.foodrecipe.utils.uikit.toolbarview.ToolBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding, IDetailVM>(),
    ToolBarView.OnToolBarViewClickListener {
    override val viewModel: IDetailVM by viewModels<DetailVM>()
    override fun getViewBinding(): ActivityDetailBinding =
        ActivityDetailBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelObservers()
        setListener()
        setUpRecyclerView()
        viewModel.setRecipe(intent.getParcelableExtra(DetailActivity::class.java.name))
    }

    override fun onStartIconClicked() {
        onBackPressed()
    }

    private fun setUpRecyclerView() {
        viewModel.adaptor.onItemClickListener = rvItemClickListener
        viewModel.adaptor.allowFullItemClickListener = true
        mViewBinding.recyclerView.adapter = viewModel.adaptor
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


    private fun setListener() {
        mViewBinding.errorView.btnRetry.setOnClickListener(this)
        mViewBinding.tbView.setOnToolBarViewClickListener(this)
        mViewBinding.swRefresh.setOnRefreshListener {
            mViewBinding.swRefresh.isRefreshing = false
            viewModel.fetchFreshData()
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
        mViewBinding.clMain.gone()
        hideShimmerLoading()
    }

    private fun setSuccessView() {
        mViewBinding.errorView.errorView.gone()
        mViewBinding.clMain.visible()
        hideShimmerLoading()
    }

    private fun setLoadingView() {
        mViewBinding.clMain.gone()
        mViewBinding.errorView.errorView.gone()
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

    private fun handleUser(user: User?) {
        user?.let {
            mViewBinding.ivImage.loadImage(user.avatarUrl)
            mViewBinding.tvDescription.text = user.bio
            mViewBinding.tvName.text = user.name
        }
    }

    private fun handleRecipeList(list: List<RecipeData>?) {
        list?.let {
            viewModel.adaptor.setList(list)
        }
    }

    private fun handleRecipe(recipeData: RecipeData?) {
        recipeData?.let { data ->
            if (data?.videoUrl == null)
                mViewBinding.ivImage.loadImage(data?.thumbnailUrl)
            else {
                mViewBinding.ivImage.loadImage(R.drawable.ic_play)
                mViewBinding.ivImage.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(data?.videoUrl)
                    ContextCompat.startActivity(this, intent, null)
                }
            }
            mViewBinding.tvDescription.text = data.description
            mViewBinding.tvName.text = data.name
            viewModel.fetchFreshData()
        }
    }

    private fun viewModelObservers() {
        observe(viewModel.recipeLists, ::handleRecipeList)
        observe(viewModel.recipeData, ::handleRecipe)
        observe(viewModel.uiState, ::handleUiState)
    }
}