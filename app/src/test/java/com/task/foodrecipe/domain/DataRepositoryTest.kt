package com.task.foodrecipe.domain

import com.task.foodrecipe.base.BaseTestCase
import com.task.foodrecipe.data.dtos.responsedtos.GitUser
import com.task.foodrecipe.data.dtos.responsedtos.RecipeResponse
import com.task.foodrecipe.data.dtos.responsedtos.User
import com.task.foodrecipe.data.remote.baseclient.ApiResponse
import com.task.foodrecipe.data.remote.microservices.foodrepos.FoodRepoApi
import com.task.foodrecipe.data.remote.microservices.foodrepos.FoodRepositoryRemote
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DataRepositoryTest : BaseTestCase() {
    // Subject under test
    lateinit var dataRepository: DataRepository

    // Use a fake UseCase to be injected into the DataRepository
    lateinit var remoteData: FoodRepoApi

    @Before
    fun setUp() {
        remoteData = mockk<FoodRepositoryRemote>()
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
                remoteData.getRecipeList(
                    query = query, size = 20, page = 0
                )
            } returns response
            //2-Call
            dataRepository = DataRepository(remoteData)
            dataRepository.getRecipes(query = query, size = 20, page = 0)

            //3-verify
            coVerify { dataRepository.getRecipes(query = query, size = 20, page = 0) }

        }
    }

    @Test
    fun `get similar recipes success`() {
        //1- Mock calls
        val recipeId = 0
        runTest {
            val response = mockk<ApiResponse.Success<RecipeResponse>> {
                every { data } returns mockk(relaxed = true)
            }


            coEvery {
                remoteData.getRecipeSimilarList(
                    recipeId
                )
            } returns response
            //2-Call
            dataRepository = DataRepository(remoteData)
            dataRepository.getSimilarRecipes(recipeId)

            //3-verify
            coVerify { dataRepository.getSimilarRecipes(recipeId) }

        }
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }
}