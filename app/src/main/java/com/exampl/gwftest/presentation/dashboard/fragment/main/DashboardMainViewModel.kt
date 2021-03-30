package com.exampl.gwftest.presentation.dashboard.fragment.main

import android.app.Application
import android.graphics.Color
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.exampl.core.domain.base.Result
import com.exampl.core.interactor.measurement.GetMeasurements
import com.exampl.core.interactor.measurement.GetTotal
import com.exampl.gwftest.R
import com.exampl.gwftest.presentation.dashboard.fragment.main.item.MeasurementItem
import com.exampl.gwftest.presentation.dashboard.fragment.main.item.TotalItem
import com.exampl.gwftest.util.binding.ListItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardMainViewModel @Inject constructor(
    application: Application,
    val getTotal: GetTotal,
    val getMeasurements: GetMeasurements
) : AndroidViewModel(application) {

    enum class Error {
        TOTAL_FAILED,
        MEASUREMENTS_FAILED
    }

    val totalData: MutableLiveData<List<ListItem>> = MutableLiveData()
    val measurementsData: MutableLiveData<List<ListItem>> = MutableLiveData()

    private val errorData: MutableLiveData<Error> = MutableLiveData()
    val error: LiveData<Error>
        get() = errorData

    var measurementFilter: String? = null
        set(filter) {
            Log.d("TAG", ": $filter")
            field = filter
            populateMeasurements()
        }

    private lateinit var unfilteredMeasurementsList: List<ListItem>

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            when (val result = getTotal.run(Unit)) {
                is Result.Success -> {
                    totalData.postValue(
                        listOf(
                            TotalItem(
                                TotalItem.TotalView(
                                    getApplication<Application>().getString(R.string.total_meters),
                                    result.data.meters.toString(),
                                    Color.parseColor("#A0AEC0")
                                )
                            ).toListItem(),
                            TotalItem(
                                TotalItem.TotalView(
                                    getApplication<Application>().getString(R.string.total_usage),
                                    result.data.volume.toString(),
                                    Color.parseColor("#63B3ED")
                                ),
                            ).toListItem(),
                            TotalItem(
                                TotalItem.TotalView(
                                    getApplication<Application>().getString(R.string.total_readouts),
                                    result.data.readouts.toString(),
                                    Color.parseColor("#68D391")
                                ),
                            ).toListItem(),
                            TotalItem(
                                TotalItem.TotalView(
                                    getApplication<Application>().getString(R.string.total_errors),
                                    result.data.error.toString(),
                                    Color.parseColor("#E53E3E")
                                ),
                            ).toListItem()
                        )
                    )
                }
                is Result.Error -> errorData.postValue(Error.TOTAL_FAILED)
            }

            when (val result = getMeasurements.run(Unit)) {
                is Result.Success -> {
                    unfilteredMeasurementsList = result.data.map {
                        MeasurementItem(
                            MeasurementItem.MeasurementView(
                                it.meterId,
                                it.mpName,
                                it.commModSerial,
                                it.commModType
                            )
                        ).toListItem()
                    }

                    populateMeasurements()
                }
                is Result.Error -> errorData.postValue(Error.MEASUREMENTS_FAILED)
            }
        }
    }

    private fun populateMeasurements() {

        val filteredList: List<ListItem> =
            if (measurementFilter.isNullOrEmpty())
                unfilteredMeasurementsList
            else {
                unfilteredMeasurementsList.filter { item ->
                    measurementFilter?.let { filter ->
                        (item.data as MeasurementItem).data.meterId.contains(filter)
                    } ?: false
                }
            }
        measurementsData.postValue(filteredList)
    }
}