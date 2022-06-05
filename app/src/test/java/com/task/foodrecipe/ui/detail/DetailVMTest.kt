package com.task.foodrecipe.ui.detail

import com.task.foodrecipe.base.BaseTestCase
import com.task.foodrecipe.base.getOrAwaitValue
import com.task.foodrecipe.data.dtos.responsedtos.RecipeResponse
import com.task.foodrecipe.data.remote.baseclient.ApiResponse
import com.task.foodrecipe.domain.DataRepository
import com.task.foodrecipe.domain.IDataRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailVMTest : BaseTestCase() {
    // Subject under test
    lateinit var viewModel: DetailVM

    // Use a fake UseCase to be injected into the viewModel
    lateinit var dataRepo: IDataRepository

    @Before
    fun setUp() {
        dataRepo = mockk<DataRepository>()
    }

    @Test
    fun `get similar recipe list success`() {
        //1- Mock calls
        val recipeId = 0
        runTest {
            val response = mockk<ApiResponse.Success<RecipeResponse>> {
                every { data } returns mockk {
                    every { results } returns listOf(mockk(), mockk())
                }
            }
            coEvery {
                dataRepo.getSimilarRecipes(
                    recipeId
                )
            } returns response
            //2-Call
            viewModel = DetailVM(dataRepo)
            viewModel.getSimilarRecipes(recipeId)

            //3-verify
            Assert.assertEquals(true, viewModel.recipeLists.getOrAwaitValue()?.isNotEmpty())
        }
    }

    @Test
    fun `get similar recipe list error`() {
        //1- Mock calls
        val recipeId = 0
        runTest {
            val response = mockk<ApiResponse.Error>()
            coEvery {
                dataRepo.getSimilarRecipes(
                    recipeId
                )
            } returns response
            //2-Call
            viewModel = DetailVM(dataRepo)
            viewModel.getSimilarRecipes(recipeId)

            //3-verify
            Assert.assertEquals(true, viewModel.recipeLists.getOrAwaitValue().isNullOrEmpty())
        }
    }

}