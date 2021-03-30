package com.exampl.gwftest.presentation.dashboard.fragment.main.item

import com.exampl.gwftest.BR
import com.exampl.gwftest.R
import com.exampl.gwftest.util.binding.DefaultItemComparator
import com.exampl.gwftest.util.binding.ListItem

class TotalItem(
    val data: TotalView
) : DefaultItemComparator<TotalItem>() {

    fun toListItem(): ListItem {
        return ListItem(
            this,
            R.layout.item_total,
            BR.totalItem
        )
    }

    override fun compareParams(otherVal: TotalItem): Boolean {
        return data.title == otherVal.data.title
    }

    override fun compareContent(otherVal: TotalItem): Boolean {
        return data == otherVal.data
    }

    data class TotalView(
        val title: String,
        val value: String,
        val color: Int
    )
}
