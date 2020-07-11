package com.app.juawcevada.whatscookin.ui.recipe

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.app.juawcevada.whatscookin.R
import com.app.juawcevada.whatscookin.common.ui.viewModelProvider
import com.app.juawcevada.whatscookin.databinding.RecipeFragmentBinding
import kotlinx.android.synthetic.main.recipe_fragment.*
import javax.inject.Inject

class RecipeFragment @Inject constructor(
    private val viewModelFactory: RecipeViewModel.Factory
) : Fragment() {

    private lateinit var recipeViewModel: RecipeViewModel
    private val fragmentArguments: RecipeFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        recipeViewModel = viewModelProvider(viewModelFactory.create(fragmentArguments.id))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return RecipeFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewActions = recipeViewModel
            viewModel = recipeViewModel
            ViewCompat.setTransitionName(recipeImage, recipeViewModel.recipeId)
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(toolbar)
    }

    private fun setUpToolbar(toolbar: Toolbar) {
        val navController = findNavController()
        toolbar.setupWithNavController(navController)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
    }
}
