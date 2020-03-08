package com.app.juawcevada.whatscookin.ui.search

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import com.app.juawcevada.whatscookin.R
import com.app.juawcevada.whatscookin.common.ui.ChipGroupAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class IngredientsChipAdapter(
    chipGroup: ChipGroup,
    onCloseListener: (String) -> Unit
) : ChipGroupAdapter<String>(
    chipGroup,
    onCloseListener,
    object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ) = oldItem == newItem
    }) {
    override fun generateChip(item: String, chipGroup: ChipGroup): Chip {
        val layoutInflater = LayoutInflater.from(chipGroup.context)
        val chip = layoutInflater.inflate(
            R.layout.chip_search_ingredient,
            chipGroup,
            false
        ) as Chip
        chip.text = item

        return chip
    }


}
