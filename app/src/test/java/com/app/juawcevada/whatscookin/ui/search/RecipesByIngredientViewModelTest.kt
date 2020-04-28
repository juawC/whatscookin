package com.app.juawcevada.whatscookin.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.juawcevada.whatscookin.common.data.Result
import com.app.juawcevada.whatscookin.common.util.Event
import com.app.juawcevada.whatscookin.domain.search.model.IngredientSearchItem
import com.app.juawcevada.whatscookin.domain.search.model.RecipeSearchItem
import com.app.juawcevada.whatscookin.domain.search.usecase.SearchForIngredientsUseCase
import com.app.juawcevada.whatscookin.domain.search.usecase.SearchForRecipesByIngredientsUseCase
import com.app.juawcevada.whatscookin.ui.util.*
import com.app.juawcevada.whatscookin.util.factories.IngredientSearchItemFactory
import com.app.juawcevada.whatscookin.util.factories.RecipeSearchItemFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
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

    @After
    fun tearDown() {
        viewModel.viewState.removeObserver(viewStateObserver)
        viewModel.viewEffect.removeObserver(viewEffectObserver)
    }

    @Test
    fun `returns a list of ingredients when searchForIngredient is successful`() =
        testCoroutineRule.runBlockingTest {
            val autoCompleteList = listOf(
                IngredientSearchItemFactory.create(name = "Chicken"),
                IngredientSearchItemFactory.create(name = "Chili")
            )
            mockSearchForIngredientsUseCase(autoCompleteList.toSuccess())

            viewModel.autoCompleteIngredient("Ch")

            inOrder(viewStateObserver, viewEffectObserver) {
                verify(viewStateObserver).onChanged(RecipesByIngredientViewModel.ViewState())
                verify(viewEffectObserver).onChanged(
                    RecipesByIngredientViewModel.ViewEffect.IngredientsAutoComplete(autoCompleteList).toEvent()
                )
            }
        }

    @Test
    fun `does not return a list of ingredients when searchForIngredients not successful`() =
        testCoroutineRule.runBlockingTest {
            mockSearchForIngredientsUseCase(Exception().toError())

            viewModel.autoCompleteIngredient("Ch")

            inOrder(viewStateObserver, viewEffectObserver) {
                verify(viewStateObserver).onChanged(RecipesByIngredientViewModel.ViewState())
                verify(viewEffectObserver, never()).onChanged(any())
            }
        }

    @Test
    fun `does not return a list of ingredients when searchForIngredient returns an empty list`() =
        testCoroutineRule.runBlockingTest {
            mockSearchForIngredientsUseCase(emptyList<IngredientSearchItem>().toSuccess())

            viewModel.autoCompleteIngredient("Ch")

            inOrder(viewStateObserver, viewEffectObserver) {
                verify(viewStateObserver).onChanged(RecipesByIngredientViewModel.ViewState())
                verify(viewEffectObserver).onChanged(
                    RecipesByIngredientViewModel.ViewEffect.IngredientsAutoComplete(
                        emptyList()
                    ).toEvent()
                )
            }
        }

    @Test
    fun `returns a recipes list when SearchForRecipes is successful`() =
        testCoroutineRule.runBlockingTest {
            val recipesList = listOf(
                RecipeSearchItemFactory.create(title = "Chicken Chili"),
                RecipeSearchItemFactory.create(title = "Chili Pasta")
            )
            mockSearchForRecipesByIngredientsUseCase(recipesList.toSuccess())

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
    fun `returns an empty recipes list when SearchForRecipes is successful but returns an empty list`() =
        testCoroutineRule.runBlockingTest {
            mockSearchForRecipesByIngredientsUseCase(emptyList<RecipeSearchItem>().toSuccess())

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
    fun `returns an empty recipes list when an ingredient is removed`() =
        testCoroutineRule.runBlockingTest {
            val recipesList = listOf(
                RecipeSearchItemFactory.create(title = "Chicken"),
                RecipeSearchItemFactory.create(title = "Chili chicken")
            )
            val recipesListItemRemoved = emptyList<RecipeSearchItem>()
            mockSearchForRecipesByIngredientsUseCase(
                recipesList.toSuccess(),
                recipesListItemRemoved.toSuccess()
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
                verify(viewEffectObserver, never()).onChanged(any())
            }
        }

    private suspend fun mockSearchForRecipesByIngredientsUseCase(vararg results: Result<List<RecipeSearchItem>>) {
        val resultsArray = results as Array<Result<List<RecipeSearchItem>>>
        whenever(searchForRecipesByIngredientsUseCase.invoke(any())).thenReturn(
            resultsArray.head,
            *resultsArray.tail
        )
    }

    private suspend fun mockSearchForIngredientsUseCase(vararg results: Result<List<IngredientSearchItem>>) {
        val resultsArray = results as Array<Result<List<IngredientSearchItem>>>
        whenever(searchForIngredientsUseCase.invoke(any())).thenReturn(
            resultsArray.head,
            *resultsArray.tail
        )
    }
}
