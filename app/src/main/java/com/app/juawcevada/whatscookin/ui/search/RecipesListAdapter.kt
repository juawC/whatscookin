package com.app.juawcevada.whatscookin.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.app.juawcevada.whatscookin.R
import com.app.juawcevada.whatscookin.common.ui.BindingListAdapter
import com.app.juawcevada.whatscookin.databinding.RecipeSearchItemBinding
import com.app.juawcevada.whatscookin.domain.search.model.RecipeSearchItem

class RecipesListAdapter(
    private val viewActions: RecipesByIngredientsViewActions
) : BindingListAdapter<RecipeSearchItem, RecipeSearchItemBinding>(RecipeSearchDiff()) {

    override fun bind(
        binding: RecipeSearchItemBinding,
        item: RecipeSearchItem,
        position: Int,
        payloads: MutableList<Any>
    ) {

        binding.item = item
        binding.viewActions = viewActions
        binding.emptyIngredients.tag = MissingIngredientsChipAdapter(binding.emptyIngredients)
        ViewCompat.setTransitionName(binding.image, item.id)
        binding.image.tag = item
    }

    override fun createBinding(parent: ViewGroup): RecipeSearchItemBinding {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DataBindingUtil.inflate(
            layoutInflater,
            R.layout.recipe_search_item,
            parent,
            false
        )
    }

    class RecipeSearchDiff : DiffUtil.ItemCallback<RecipeSearchItem>() {
        override fun areItemsTheSame(
            oldItem: RecipeSearchItem,
            newItem: RecipeSearchItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecipeSearchItem,
            newItem: RecipeSearchItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}
