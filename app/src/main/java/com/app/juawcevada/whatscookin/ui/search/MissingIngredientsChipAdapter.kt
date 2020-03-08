package com.app.juawcevada.whatscookin.ui.search

import android.view.LayoutInflater
import com.app.juawcevada.whatscookin.R
import com.app.juawcevada.whatscookin.domain.search.model.Ingredient
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MissingIngredientsChipAdapter(private val chipGroup: ChipGroup) {

    fun submitList(items: List<Ingredient>) {
        chipGroup.removeAllViews()
        items.forEach(::addChip)
    }

    private fun addChip(item: Ingredient) {
        val layoutInflater = LayoutInflater.from(chipGroup.context)
        val chip = layoutInflater.inflate(
            R.layout.chip_missing_ingredient,
            chipGroup,
            false
        ) as Chip
        chip.text = item.name
        chipGroup.addView(chip)
    }
}
