package com.app.juawcevada.whatscookin.ui.recipe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.juawcevada.whatscookin.R
import com.app.juawcevada.whatscookin.common.data.Result
import com.app.juawcevada.whatscookin.domain.recipe.model.Recipe
import com.app.juawcevada.whatscookin.domain.recipe.usecase.GetRecipeUseCase
import com.app.juawcevada.whatscookin.ui.util.TestCoroutineRule
import com.app.juawcevada.whatscookin.ui.util.toError
import com.app.juawcevada.whatscookin.ui.util.toSuccess
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RecipeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var viewModel: RecipeViewModel
    @Mock
    private lateinit var getRecipeUseCase: GetRecipeUseCase
    @Mock
    private lateinit var viewStateObserver: Observer<RecipeViewModel.ViewState>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `returns an recipe when getRecipe is successful`() =
        testCoroutineRule.runBlockingTest {
            mockGetRecipeUseCase(Recipe(title = "title").toSuccess())
            initViewModel()

            verify(viewStateObserver).onChanged(
                RecipeViewModel.ViewState(
                    isLoading = false,
                    recipe = Recipe(title = "title")
                )
            )
        }

    @Test
    fun `returns an error when getRecipe is not successful`() =
        testCoroutineRule.runBlockingTest {
            mockGetRecipeUseCase(Exception().toError())
            initViewModel()

            verify(viewStateObserver).onChanged(
                RecipeViewModel.ViewState(
                    isLoading = false,
                    errorMessage = R.string.error_message
                )
            )
        }

    private fun initViewModel() {
        viewModel = RecipeViewModel(
            "id",
            getRecipeUseCase
        ).apply {
            viewState.observeForever(viewStateObserver)
        }
    }

    private suspend fun mockGetRecipeUseCase(result: Result<Recipe>) {
        whenever(getRecipeUseCase.invoke(any())).thenReturn(result)
    }
}
