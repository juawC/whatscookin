package com.app.juawcevada.whatscookin.ui.recipe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.juawcevada.whatscookin.R
import com.app.juawcevada.whatscookin.common.data.Result
import com.app.juawcevada.whatscookin.domain.recipe.model.Recipe
import com.app.juawcevada.whatscookin.domain.recipe.usecase.GetRecipeUseCase
import com.app.juawcevada.whatscookin.ui.util.TestCoroutineRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.Exception

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
    fun `verify state after getRecipeUseCase returns an recipe`() =
        testCoroutineRule.runBlockingTest {
            whenever(getRecipeUseCase.invoke(any())).thenReturn(Result.Success(Recipe(title = "title")))
            initViewModel()

            verify(viewStateObserver).onChanged(RecipeViewModel.ViewState(
                isLoading = false,
                recipe = Recipe(title = "title")
            ))
        }

    @Test
    fun `verify state after getRecipeUseCase returns an error`() =
        testCoroutineRule.runBlockingTest {
            whenever(getRecipeUseCase.invoke(any())).thenReturn(Result.Error(Exception()))
            initViewModel()

            verify(viewStateObserver).onChanged(RecipeViewModel.ViewState(
                isLoading = false,
                errorMessage = R.string.error_message
            ))
        }

    private fun initViewModel() {
        viewModel = RecipeViewModel(
            "id",
            getRecipeUseCase
        ).apply {
            viewState.observeForever(viewStateObserver)
        }
    }
}
