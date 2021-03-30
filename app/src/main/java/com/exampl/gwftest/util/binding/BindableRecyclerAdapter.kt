package com.exampl.gwftest.util.binding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class BindableRecyclerAdapter : ListAdapter<ListItem, BindingViewHolder>(DiffCallback()) {

    interface ItemClickListener {
        fun onItemClicked(view: View, position: Int, item: ListItem)
    }

    var itemClickListener: ItemClickListener? = null

    override fun getItemViewType(position: Int): Int {
        return currentList[position].layoutId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
        ).apply {
            itemClickListener?.let {
                itemView.setOnClickListener { view ->
                    it.onItemClicked(
                        view,
                        adapterPosition,
                        currentList[adapterPosition]
                    )
                }
            }
        }
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val currentItem = currentList[position]
        currentItem.bind(holder.binding)
        holder.binding.executePendingBindings()
    }

}

class BindingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

class ListItem(
    val data: Any,
    @LayoutRes val layoutId: Int,
    val variableId: Int,
) {
    fun bind(binding: ViewDataBinding) {
        binding.setVariable(variableId, data)
    }
}

interface RecyclerItemComparator {
    fun isSameItem(other: Any): Boolean
    fun isSameContent(other: Any): Boolean
}

abstract class DefaultItemComparator<T : Any> :
    RecyclerItemComparator {

    abstract fun compareParams(otherVal: T): Boolean
    abstract fun compareContent(otherVal: T): Boolean

    inline fun <reified T> sameType(other: Any): Boolean {
        return other::javaClass == T::class.java
    }

    override fun isSameItem(other: Any): Boolean {
        if (this === other) return true
        if (javaClass !== other.javaClass) return false
        return compareParams(other as T)
    }

    override fun isSameContent(other: Any): Boolean {
        return compareContent(other as T)
    }

}

private class DiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(
        oldItem: ListItem,
        newItem: ListItem
    ): Boolean {
        val oldData = oldItem.data
        val newData = newItem.data

        return if (oldData is RecyclerItemComparator
            && newData is RecyclerItemComparator
        ) {
            oldData.isSameItem(newData)
        } else oldData == newData
    }

    override fun areContentsTheSame(
        oldItem: ListItem,
        newItem: ListItem
    ): Boolean {
        val oldData = oldItem.data
        val newData = newItem.data
        return if (oldData is RecyclerItemComparator
            && newData is RecyclerItemComparator
        ) oldData.isSameContent(newData)
        else oldData == newData
    }
}