package com.exampl.gwftest.presentation.dashboard.fragment.detail.item


data class DetailsItem(
        val serial: String,
        val type: String,
        val lastReadOut: String,
        val volume: String,
        val battery: String
    )