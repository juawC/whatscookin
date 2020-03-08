package com.app.juawcevada.whatscookin.ui.search

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.juawcevada.whatscookin.R
import com.app.juawcevada.whatscookin.common.ui.BindingListAdapter
import com.app.juawcevada.whatscookin.databinding.IngredientSearchItemBinding
import com.app.juawcevada.whatscookin.domain.search.model.IngredientSearchItem
import com.otaliastudios.autocomplete.Autocomplete
import com.otaliastudios.autocomplete.AutocompleteCallback
import com.otaliastudios.autocomplete.RecyclerViewPresenter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IngredientSearchAutoCompleteAdapter(
    editText: EditText,
    private val lifecycle: Lifecycle,
    private val viewActions: RecipesByIngredientsViewActions
) : RecyclerViewPresenter<IngredientSearchItem>(editText.context) {

    companion object {
        const val SEARCH_DELAY = 150L
    }

    private var searchJob: Job? = null

    init {
        val elevation = 6f
        val backgroundDrawable: Drawable = ColorDrawable(Color.WHITE)
        val callback: AutocompleteCallback<IngredientSearchItem> =
            object : AutocompleteCallback<IngredientSearchItem> {
                override fun onPopupItemClicked(
                    editable: Editable,
                    item: IngredientSearchItem
                ): Boolean {
                    editable.clear()
                    viewActions.addIngredient(item.name)
                    return true
                }

                override fun onPopupVisibilityChanged(shown: Boolean) {}
            }

        Autocomplete.on<IngredientSearchItem>(editText)
            .with(elevation)
            .with(backgroundDrawable)
            .with(this)
            .with(callback)
            .build()
    }

    private val recyclerAdapter: RecyclerAdapter by lazy { RecyclerAdapter { dispatchClick(it) } }

    override fun instantiateAdapter() = recyclerAdapter

    fun submitList(ingredients: List<IngredientSearchItem>) {
        recyclerAdapter.submitList(ingredients)
    }

    override fun onQuery(query: CharSequence?) {
        searchJob?.cancel()
        searchJob = lifecycle.coroutineScope.launch {
            delay(SEARCH_DELAY)
            viewActions.autoCompleteIngredient(query.toString())
        }
    }

    class RecyclerAdapter(
        private val onItemClick: (IngredientSearchItem) -> Unit
    ) : BindingListAdapter<IngredientSearchItem, IngredientSearchItemBinding>(IngredientSearchDiff()) {
        override fun bind(
            binding: IngredientSearchItemBinding,
            item: IngredientSearchItem,
            position: Int,
            payloads: MutableList<Any>
        ) {
            binding.item = item
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            super.onAttachedToRecyclerView(recyclerView)
            recyclerView.itemAnimator = null
        }

        override fun createBinding(parent: ViewGroup): IngredientSearchItemBinding {
            val layoutInflater = LayoutInflater.from(parent.context)
            return DataBindingUtil.inflate(
                layoutInflater,
                R.layout.ingredient_search_item,
                parent,
                false
            )
        }

        class IngredientSearchDiff : DiffUtil.ItemCallback<IngredientSearchItem>() {
            override fun areItemsTheSame(
                oldItem: IngredientSearchItem,
                newItem: IngredientSearchItem
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: IngredientSearchItem,
                newItem: IngredientSearchItem
            ): Boolean {
                return oldItem == newItem
            }
        }

    }

}
