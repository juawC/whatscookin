package com.app.juawcevada.whatscookin.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.juawcevada.whatscookin.common.data.Result
import com.app.juawcevada.whatscookin.common.util.Event
import com.app.juawcevada.whatscookin.domain.search.model.RecipeSearchItem
import com.app.juawcevada.whatscookin.domain.search.usecase.SearchForIngredientsUseCase
import com.app.juawcevada.whatscookin.domain.search.usecase.SearchForRecipesByIngredientsUseCase
import com.app.juawcevada.whatscookin.ui.util.TestCoroutineRule
import com.app.juawcevada.whatscookin.util.factories.IngredientSearchItemFactory
import com.app.juawcevada.whatscookin.util.factories.RecipeSearchItemFactory
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RecipesByIngredientViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var viewModel: RecipesByIngredientViewModel
    @Mock
    private lateinit var searchForRecipesByIngredientsUseCase: SearchForRecipesByIngredientsUseCase
    @Mock
    private lateinit var searchForIngredientsUseCase: SearchForIngredientsUseCase
    @Mock
    private lateinit var viewStateObserver: Observer<RecipesByIngredientViewModel.ViewState>
    @Mock
    private lateinit var viewEffectObserver: Observer<Event<RecipesByIngredientViewModel.ViewEffect>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = RecipesByIngredientViewModel(
            searchForRecipesByIngredientsUseCase,
            searchForIngredientsUseCase
        ).apply {
            viewState.observeForever(viewStateObserver)
            viewEffect.observeForever(viewEffectObserver)
        }
    }

    @Test
    fun `verify state after autoCompleteIngredient and SearchForIngredientsUseCase returns a list of elements`() =
        testCoroutineRule.runBlockingTest {
            val autoCompleteList = listOf(
                IngredientSearchItemFactory.create(name = "Chicken"),
                IngredientSearchItemFactory.create(name = "Chili")
            )
            whenever(searchForIngredientsUseCase.invoke(any())).thenReturn(Result.Success(autoCompleteList))

            viewModel.autoCompleteIngredient("Ch")

            inOrder(viewStateObserver, viewEffectObserver) {
                verify(viewStateObserver).onChanged(RecipesByIngredientViewModel.ViewState())
                verify(viewEffectObserver).onChanged(
                    Event(
                        RecipesByIngredientViewModel.ViewEffect.IngredientsAutoComplete(
                            autoCompleteList
                        )
                    )
                )
            }
        }

    @Test
    fun `verify state after autoCompleteIngredient and SearchForIngredientsUseCase returns an error`()  =
        testCoroutineRule.runBlockingTest {
            whenever(searchForIngredientsUseCase.invoke(any())).thenReturn(Result.Error(Exception()))

            viewModel.autoCompleteIngredient("Ch")

            inOrder(viewStateObserver, viewEffectObserver) {
                verify(viewStateObserver).onChanged(RecipesByIngredientViewModel.ViewState())
                verify(viewEffectObserver, never()).onChanged(any())
            }
        }

    @Test
    fun `verify state after autoCompleteIngredient and SearchForRecipesByIngredientsUseCase returns empty list`() =
        testCoroutineRule.runBlockingTest {
            whenever(searchForIngredientsUseCase.invoke(any())).thenReturn(Result.Success(emptyList()))

            viewModel.autoCompleteIngredient("Ch")

            inOrder(viewStateObserver, viewEffectObserver) {
                verify(viewStateObserver).onChanged(RecipesByIngredientViewModel.ViewState())
                verify(viewEffectObserver).onChanged(
                    Event(
                        RecipesByIngredientViewModel.ViewEffect.IngredientsAutoComplete(
                            emptyList()
                        )
                    )
                )
            }
        }

    @Test
    fun `verify state after addIngredient and SearchForRecipesByIngredientsUseCase returns list with ingredients`() =
        testCoroutineRule.runBlockingTest {
            val recipesList = listOf(
                RecipeSearchItemFactory.create(title = "Chicken Chili"),
                RecipeSearchItemFactory.create(title = "Chili Pasta")
            )
            whenever(searchForRecipesByIngredientsUseCase.invoke(any())).thenReturn(Result.Success(recipesList))

            viewModel.addIngredient("Chili")

            inOrder(viewStateObserver, viewEffectObserver) {
                verify(viewStateObserver).onChanged(
                    RecipesByIngredientViewModel.ViewState(
                        isLoading = true,
                        ingredients = listOf("Chili")
                    )
                )
                verify(viewStateObserver).onChanged(
                    RecipesByIngredientViewModel.ViewState(
                        isLoading = false,
                        ingredients = listOf("Chili"),
                        recipes = recipesList
                    )
                )
                verify(viewEffectObserver, never()).onChanged(any())
            }
        }


    @Test
    fun `verify state after addIngredient and SearchForRecipesByIngredientsUseCase returns empty list`()  =
        testCoroutineRule.runBlockingTest {
            whenever(searchForRecipesByIngredientsUseCase.invoke(any())).thenReturn(Result.Success(
                emptyList()))

            viewModel.addIngredient("Chili")
            inOrder(viewStateObserver, viewEffectObserver) {
                verify(viewStateObserver).onChanged(
                    RecipesByIngredientViewModel.ViewState(
                        isLoading = true,
                        ingredients = listOf("Chili")
                    )
                )
                verify(viewStateObserver).onChanged(
                    RecipesByIngredientViewModel.ViewState(
                        isLoading = false,
                        ingredients = listOf("Chili"),
                        recipes = emptyList()
                    )
                )
                verify(viewEffectObserver, never()).onChanged(any())
            }
        }

    @Test
    fun `verify state after removeIngredient and SearchForRecipesByIngredientsUseCase returns emptyList`() =
        testCoroutineRule.runBlockingTest {
            val recipesList = listOf(
                RecipeSearchItemFactory.create(title = "Chicken"),
                RecipeSearchItemFactory.create(title = "Chili chicken")
            )

            val recipesListItemRemoved = emptyList<RecipeSearchItem>()
            whenever(searchForRecipesByIngredientsUseCase.invoke(any())).thenReturn(
                Result.Success(recipesList),
                Result.Success(recipesListItemRemoved)
            )

            viewModel.addIngredient("Chili")
            viewModel.removeIngredient("Chili")

            inOrder(viewStateObserver, viewEffectObserver) {
                verify(viewStateObserver).onChanged(RecipesByIngredientViewModel.ViewState(
                    isLoading = true,
                    ingredients = listOf("Chili")
                ))
                verify(viewStateObserver).onChanged(RecipesByIngredientViewModel.ViewState(
                    isLoading = false,
                    ingredients = listOf("Chili"),
                    recipes = recipesList
                ))
                verify(viewStateObserver).onChanged(RecipesByIngredientViewModel.ViewState(
                    isLoading = false,
                    isRefreshing = true,
                    ingredients = emptyList(),
                    recipes = recipesList
                ))
                verify(viewStateObserver).onChanged(RecipesByIngredientViewModel.ViewState(
                    isLoading = false,
                    isRefreshing = false,
                    ingredients = emptyList(),
                    recipes = emptyList()
                ))
            }

            verify(viewEffectObserver, never()).onChanged(any())
        }
}
