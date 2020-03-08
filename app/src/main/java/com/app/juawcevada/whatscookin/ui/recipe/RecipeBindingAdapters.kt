package com.app.juawcevada.whatscookin.ui.recipe

import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.app.juawcevada.whatscookin.R
import com.app.juawcevada.whatscookin.databinding.IngredientRecipeItemBinding
import com.app.juawcevada.whatscookin.domain.recipe.model.ExtendedIngredient

@BindingAdapter("setRecipeIngredients")
fun setIngredients(parent: LinearLayout, list: List<ExtendedIngredient>?) {
    val layoutInflater = LayoutInflater.from(parent.context)
    list?.forEach { ingredient ->
        val binding: IngredientRecipeItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.ingredient_recipe_item,
            parent,
            false
        )
        binding.item = ingredient
        parent.addView(binding.root)
    }
}
