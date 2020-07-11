package com.app.juawcevada.whatscookin.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.juawcevada.whatscookin.R
import com.app.juawcevada.whatscookin.R.id.list
import com.app.juawcevada.whatscookin.util.createFactoryWithNavController
import com.app.juawcevada.whatscookin.util.createNavControllerMock
import com.app.juawcevada.whatscookin.common.util.Event
import com.app.juawcevada.whatscookin.util.*
import com.app.juawcevada.whatscookin.util.factories.search.RecipeSearchItemFactory
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class RecipesByIngredientFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RecipesByIngredientViewModel
    private lateinit var viewState: MutableLiveData<RecipesByIngredientViewModel.ViewState>
    private lateinit var viewEffect: MutableLiveData<Event<RecipesByIngredientViewModel.ViewEffect>>
    private lateinit var fragmentScenario: FragmentScenario<RecipesByIngredientFragment>
    private val mockNavController: NavController = createNavControllerMock(R.id.recipeFragment)

    @Test
    fun showRecipes() {
        startFragment()

        viewState.value = RecipesByIngredientViewModel.ViewState(
            ingredients = listOf("Chicken"),
            recipes = listOf(
                RecipeSearchItemFactory.create(title = "Chicken bacon!"),
                RecipeSearchItemFactory.create(title = "Chicken with veggies!")
            )
        )

        list onRecyclerViewPosition 0 checkThatMatches all {
            matcher { hasDescendant(withText("Chicken bacon!")) }
        }
        list onRecyclerViewPosition 1 checkThatMatches all {
            matcher { hasDescendant(withText("Chicken with veggies!")) }
        }
    }

    private fun initViewModel() {
        viewState = MutableLiveData()
        viewEffect = MutableLiveData()

        viewModel = mock {
            on { viewState } doReturn this@RecipesByIngredientFragmentTest.viewState
            on { viewEffect } doReturn this@RecipesByIngredientFragmentTest.viewEffect
        }
    }

    private fun startFragment() {
        initViewModel()
        fragmentScenario = launchFragmentInContainer(
            themeResId = R.style.AppTheme,
            factory = createFactoryWithNavController(mockNavController) {
                RecipesByIngredientFragment(viewModel.createTestFactory())
            }
        )
    }
}
