package com.task.foodrecipe.ui.dashaboard

import com.task.foodrecipe.base.BaseTestCase
import com.task.foodrecipe.base.getOrAwaitValue
import com.task.foodrecipe.data.dtos.responsedtos.RecipeResponse
import com.task.foodrecipe.data.remote.baseclient.ApiResponse
import com.task.foodrecipe.domain.DataRepository
import com.task.foodrecipe.domain.IDataRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class DashboardVMTest : BaseTestCase() {
    // Subject under test
    lateinit var viewModel: DashboardVM

    // Use a fake UseCase to be injected into the viewModel
    lateinit var dataRepo: IDataRepository

    @Before
    fun setUp() {
        dataRepo = mockk<DataRepository>()
    }

    @Test
    fun `get recipe list success`() {
        //1- Mock calls
        val query = ""
        runTest {
            val response = mockk<ApiResponse.Success<RecipeResponse>> {
                every { data } returns mockk {
                    every { results } returns listOf(mockk(), mockk())
                }
            }
            coEvery {
                dataRepo.getRecipes(
                    query = query, size = 20, page = 0
                )
            } returns response
            //2-Call
            viewModel = DashboardVM(dataRepo)
            viewModel.getRecipes(query = query, pageSize = 20, pageNo = 0)

            //3-verify
            Assert.assertEquals(false, viewModel.recipeLists.getOrAwaitValue().isNullOrEmpty())
        }
    }

    @Test
    fun `get recipe list error`() {
        //1- Mock calls
        val query = ""
        runTest {
            val response = mockk<ApiResponse.Error>()
            coEvery {
                dataRepo.getRecipes(
                    query = query, size = 20, page = 0
                )
            } returns response
            //2-Call
            viewModel = DashboardVM(dataRepo)
            viewModel.getRecipes(query = query, pageSize = 20, pageNo = 0)

            //3-verify
            Assert.assertEquals(true, viewModel.recipeLists.getOrAwaitValue().isNullOrEmpty())
        }
    }


    @After
    fun cleanUp() {
        clearAllMocks()
    }
}