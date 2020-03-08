package com.app.juawcevada.whatscookin.ui.search

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.juawcevada.whatscookin.domain.search.model.Ingredient
import com.app.juawcevada.whatscookin.domain.search.model.RecipeSearchItem
import com.google.android.material.chip.ChipGroup

@BindingAdapter("setRecipes")
fun setPosts(recyclerView: RecyclerView, list: List<RecipeSearchItem>?) {
    (recyclerView.adapter as RecipesListAdapter).submitList(list)
}

@BindingAdapter("setMissingIngredients")
fun setMissingIngredients(chipGroup: ChipGroup, list: List<Ingredient>?) {
    (chipGroup.tag as MissingIngredientsChipAdapter).submitList(list ?: emptyList())
}

@BindingAdapter("setSearchIngredients")
fun setSearchIngredients(chipGroup: ChipGroup, list: List<String>?) {
    (chipGroup.tag as IngredientsChipAdapter).submitList(list ?: emptyList())
}