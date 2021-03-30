package com.exampl.gwftest.presentation.dashboard.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.exampl.gwftest.BuildConfig
import com.exampl.gwftest.R
import com.exampl.gwftest.databinding.FragmentDashboardDetailsBinding
import com.exampl.gwftest.util.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import org.osmdroid.config.Configuration
import org.osmdroid.views.CustomZoomButtonsController
import javax.inject.Inject

class DashboardDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val viewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(DashboardDetailsViewModel::class.java)

        arguments?.let {
            viewModel.getMeasurement(
                DashboardDetailsFragmentArgs.fromBundle(it).meterId
            )
        }

        val binding = FragmentDashboardDetailsBinding.inflate(inflater, container, false).apply {
            Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID
            map.controller.setZoom(20.0)
            map.zoomController.setVisibility(CustomZoomButtonsController.Visibility.SHOW_AND_FADEOUT)

            this.viewModel = viewModel
            lifecycleOwner = this@DashboardDetailsFragment.viewLifecycleOwner
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(
                binding.parent,
                getString(R.string.something_went_wrong),
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return binding.root
    }

}