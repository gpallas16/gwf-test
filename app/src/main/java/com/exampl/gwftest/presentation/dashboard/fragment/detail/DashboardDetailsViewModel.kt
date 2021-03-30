package com.exampl.gwftest.presentation.dashboard.fragment.detail

import android.app.Application
import android.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.exampl.core.domain.base.Result
import com.exampl.core.domain.entity.Measurement
import com.exampl.core.domain.entity.State
import com.exampl.core.interactor.measurement.GetMeasurement
import com.exampl.gwftest.R
import com.exampl.gwftest.presentation.dashboard.fragment.detail.item.DetailsItem
import com.exampl.gwftest.presentation.dashboard.fragment.detail.item.StateItem
import com.exampl.gwftest.util.binding.ListItem
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DashboardDetailsViewModel @Inject constructor(
    application: Application,
    private val getMeasurement: GetMeasurement
) : AndroidViewModel(application) {

    enum class Error {
        MEASUREMENT_ERROR
    }

    val geolocationData: MutableLiveData<GeoPoint> = MutableLiveData()
    val detailsData: MutableLiveData<DetailsItem> = MutableLiveData()
    val stateListData: MutableLiveData<List<ListItem>> = MutableLiveData()

    private val errorData: MutableLiveData<Error> = MutableLiveData()
    val error: LiveData<Error>
        get() = errorData

    fun getMeasurement(meterId: String) {
        viewModelScope.launch {
            when (val result = getMeasurement.run(meterId)) {
                is Result.Success -> {
                    geolocationData.postValue(GeoPoint(result.data.lat, result.data.lng))

                    detailsData.postValue(
                        DetailsItem(
                            result.data.commModSerial,
                            result.data.commModType,
                            result.data.lastEntry?.let {
                                SimpleDateFormat(
                                    "EEE, MMM dd, yyyy HH:mm aa",
                                    Locale.ENGLISH
                                ).format(
                                    SimpleDateFormat(
                                        "yyyy-MM-dd'T'HH:mm:ss",
                                        Locale.ENGLISH
                                    ).parse(it)!!
                                )
                            } ?: "-",
                            "${result.data.volume} m3",
                            result.data.batteryLifetime?.let { "($it)" } ?: "",
                        )
                    )

                    stateListData.postValue(createStateList(result.data.state))
                }
                is Result.Error -> errorData.postValue(Error.MEASUREMENT_ERROR)
            }
        }
    }

    private fun createStateList(state : State) : List<ListItem>{
        val stateList: MutableList<ListItem> = mutableListOf()

        fun getItem(title: String, value: String, color: Int): ListItem {
            return StateItem(StateItem.StateView(title, value, color)).toListItem()
        }

        fun getColor(b: Boolean): Int = if (!b) Color.GREEN else Color.RED

        state.encoderError?.let {
            stateList.add(
                getItem(
                    getApplication<Application>().getString(R.string.encoder_error),
                    it.toString(),
                    getColor(it)
                )
            )
        }

        state.usWaterLevel?.let {
            stateList.add(
                getItem(
                    getApplication<Application>().getString(R.string.us_water_level),
                    it.toString(),
                    getColor(it)
                )
            )
        }

        state.vSensorCommTimOut?.let {
            stateList.add(
                getItem(
                    getApplication<Application>().getString(R.string.v_sensor),
                    it.toString(),
                    getColor(it)
                )
            )
        }

        state.waterLevelError?.let {
            stateList.add(
                getItem(
                    getApplication<Application>().getString(R.string.water_level_error),
                    it.toString(),
                    getColor(it)
                )
            )
        }

        state.tAirError?.let {
            stateList.add(
                getItem(
                    getApplication<Application>().getString(R.string.t_air_error),
                    it.toString(),
                    getColor(it)
                )
            )
        }

        state.tWaterError?.let {
            stateList.add(
                getItem(
                    getApplication<Application>().getString(R.string.t_water_error),
                    it.toString(),
                    getColor(it)
                )
            )
        }

        state.wAirError?.let {
            stateList.add(
                getItem(
                    getApplication<Application>().getString(R.string.w_air_error),
                    it.toString(),
                    getColor(it)
                )
            )
        }

        state.wWaterError?.let {
            stateList.add(
                getItem(
                    getApplication<Application>().getString(R.string.w_water_error),
                    it.toString(),
                    getColor(it)
                )
            )
        }

        state.velocityError?.let {
            stateList.add(
                getItem(
                    getApplication<Application>().getString(R.string.velocity_error),
                    it.toString(),
                    getColor(it)
                )
            )
        }

        state.systemError?.let {
            stateList.add(
                getItem(
                    getApplication<Application>().getString(R.string.system_error),
                    it.toString(),
                    getColor(it)
                )
            )
        }

        return stateList
    }

}