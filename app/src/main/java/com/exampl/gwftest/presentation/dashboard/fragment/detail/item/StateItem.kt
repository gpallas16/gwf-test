package com.exampl.gwftest.presentation.dashboard.fragment.detail.item

import com.exampl.gwftest.BR
import com.exampl.gwftest.R
import com.exampl.gwftest.util.binding.DefaultItemComparator
import com.exampl.gwftest.util.binding.ListItem

class StateItem(
    val data: StateView
) : DefaultItemComparator<StateItem>() {

    fun toListItem(): ListItem {
        return ListItem(
            this,
            R.layout.item_state,
            BR.stateItem
        )
    }

    override fun compareParams(otherVal: StateItem): Boolean {
        return data.title == otherVal.data.title
    }

    override fun compareContent(otherVal: StateItem): Boolean {
        return data == otherVal.data
    }

    data class StateView(
        val title: String,
        val value: String,
        val color: Int
    )
}
