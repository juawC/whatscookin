package com.app.juawcevada.whatscookin.common.ui

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

abstract class ChipGroupAdapter<T>(
    private val chipGroup: ChipGroup,
    private val onCloseListener: (T) -> Unit,
    callback: DiffUtil.ItemCallback<T>
) : ListUpdateCallback {
    private val asyncList = AsyncListDiffer<T>(this, AsyncDifferConfig.Builder<T>(callback).build())
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onInserted(position: Int, count: Int) {
        apply(position, count) { index ->
            addChip(index)
        }
    }

    override fun onRemoved(position: Int, count: Int) {
        chipGroup.removeViews(position, count)
    }

    abstract fun generateChip(item: T, chipGroup: ChipGroup): Chip

    fun submitList(items: List<T>) {
        asyncList.submitList(items)
    }

    private fun apply(position: Int, count: Int, action: (index: Int) -> Unit) {
        val endPosition = position + count - 1
        if (endPosition < 0) return

        for (index in position..(endPosition)) {
            action(index)
        }
    }

    private fun addChip(position: Int) {
        val item = asyncList.currentList[position]
        val chip = generateChip(item, chipGroup)
        chipGroup.addView(chip as View, position)
        chip.setOnCloseIconClickListener { onCloseListener(item) }
    }
}
