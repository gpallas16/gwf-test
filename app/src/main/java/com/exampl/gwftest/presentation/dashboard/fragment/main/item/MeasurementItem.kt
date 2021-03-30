package com.exampl.gwftest.presentation.dashboard.fragment.main.item

import com.exampl.gwftest.R
import com.exampl.gwftest.BR
import com.exampl.gwftest.util.binding.DefaultItemComparator
import com.exampl.gwftest.util.binding.ListItem

class MeasurementItem(
    val data: MeasurementView
) : DefaultItemComparator<MeasurementItem>() {

    fun toListItem(): ListItem {
        return ListItem(
            this,
            R.layout.item_measurement,
            BR.measurementItem
        )
    }

    override fun compareParams(otherVal: MeasurementItem): Boolean {
        return data.meterId == otherVal.data.meterId
    }

    override fun compareContent(otherVal: MeasurementItem): Boolean {
        return data == otherVal.data
    }

    data class MeasurementView(
        val meterId:String,
        val location:String,
        val serial:String,
        val type:String
    )
}
