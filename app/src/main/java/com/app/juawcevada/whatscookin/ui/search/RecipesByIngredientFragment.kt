package com.app.juawcevada.whatscookin.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.juawcevada.whatscookin.common.ui.observeEvent
import com.app.juawcevada.whatscookin.databinding.RecipesByIngredientFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesByIngredientFragment : Fragment() {

    private val recipesViewModel: RecipesByIngredientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return RecipesByIngredientFragmentBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            viewActions = recipesViewModel
            viewModel = recipesViewModel
            list.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            list.adapter = RecipesListAdapter(recipesViewModel)
            searchIngredients.tag = IngredientsChipAdapter(searchIngredients, recipesViewModel::removeIngredient)

            val autoCompleteAdapter = IngredientSearchAutoCompleteAdapter(
                searchBar,
                viewLifecycleOwner.lifecycle,
                recipesViewModel
            )
            viewLifecycleOwner.observeEvent(recipesViewModel.viewEffect) {
                when (it) {
                    is RecipesByIngredientViewModel.ViewEffect.IngredientsAutoCompleteUpdate -> {
                        autoCompleteAdapter.submitList(it.ingredients)
                    }
                    is RecipesByIngredientViewModel.ViewEffect.NavigateToDetail -> {
                        navigateToDetail(it)
                    }
                }
            }
        }.root
    }

    private fun RecipesByIngredientFragmentBinding.navigateToDetail(
        navigateToDetailEffect: RecipesByIngredientViewModel.ViewEffect.NavigateToDetail
    ) {
        val action =
            RecipesByIngredientFragmentDirections
                .actionRecipesByIngredientFragmentToRecipeFragment(navigateToDetailEffect.recipe.id)
        val image = list.findViewWithTag<ImageView>(navigateToDetailEffect.recipe)
        val extras = FragmentNavigatorExtras(image to navigateToDetailEffect.recipe.id)
        findNavController().navigate(action, extras)
    }
}
