package com.app.juawcevada.whatscookin.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.juawcevada.whatscookin.common.ui.observeEvent
import com.app.juawcevada.whatscookin.common.ui.viewModelProvider
import com.app.juawcevada.whatscookin.databinding.RecipesByIngredientFragmentBinding
import com.app.juawcevada.whatscookin.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class RecipesByIngredientFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<RecipesByIngredientViewModel>
    private lateinit var recipesViewModel: RecipesByIngredientViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        recipesViewModel = viewModelProvider(viewModelFactory)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return RecipesByIngredientFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewActions = recipesViewModel
            viewModel = recipesViewModel
            list.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            list.adapter = RecipesListAdapter(recipesViewModel)
            searchIngredients.tag =
                IngredientsChipAdapter(searchIngredients, recipesViewModel::removeIngredient)

            val adapter = IngredientSearchAutoCompleteAdapter(
                searchBar,
                viewLifecycleOwner.lifecycle,
                recipesViewModel
            )
            viewLifecycleOwner.observeEvent(recipesViewModel.viewEffect) {
                when (it) {
                    is RecipesByIngredientViewModel.ViewEffect.IngredientsAutoComplete -> {
                        adapter.submitList(it.ingredients)
                    }
                    is RecipesByIngredientViewModel.ViewEffect.NavigateToDetail -> {
                        val action =
                            RecipesByIngredientFragmentDirections
                                .actionRecipesByIngredientFragmentToRecipeFragment(it.recipe.id)
                        val image = list.findViewWithTag<ImageView>(it.recipe)
                        val extras = FragmentNavigatorExtras(image to it.recipe.id)
                        findNavController().navigate(action, extras)
                    }
                }
            }

        }.root
    }
}
