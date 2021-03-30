package com.exampl.gwftest.presentation.dashboard.fragment.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exampl.gwftest.R
import com.exampl.gwftest.databinding.FragmentDashboardMainBinding
import com.exampl.gwftest.presentation.dashboard.DashboardActivity
import com.exampl.gwftest.presentation.dashboard.DashboardViewModel
import com.exampl.gwftest.presentation.dashboard.fragment.main.item.MeasurementItem
import com.exampl.gwftest.util.ViewModelProviderFactory
import com.exampl.gwftest.util.binding.BindableRecyclerAdapter
import com.exampl.gwftest.util.binding.ListItem
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DashboardMainFragment : DaggerFragment(), BindableRecyclerAdapter.ItemClickListener {


    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var binding: FragmentDashboardMainBinding
    private lateinit var viewModel: DashboardMainViewModel
    private lateinit var sharedViewModel: DashboardViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context) {
            is DashboardActivity -> {
                sharedViewModel =
                    ViewModelProvider(context, viewModelProviderFactory).get(
                        DashboardViewModel::class.java
                    )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this@DashboardMainFragment, viewModelProviderFactory).get(
            DashboardMainViewModel::class.java
        )

        sharedViewModel.search.observe(viewLifecycleOwner) {
            viewModel.measurementFilter = it
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(
                binding.parent,
                getString(R.string.something_went_wrong),
                Snackbar.LENGTH_SHORT
            ).show()
        }

        binding = FragmentDashboardMainBinding.inflate(layoutInflater, container, false).apply {
            itemClickListener = this@DashboardMainFragment
            viewModel = this@DashboardMainFragment.viewModel
            sharedViewModel = this@DashboardMainFragment.sharedViewModel
            lifecycleOwner = this@DashboardMainFragment.viewLifecycleOwner
            dashboardTotalRv.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

        return binding.root
    }

    override fun onItemClicked(view: View, position: Int, item: ListItem) {
        val action =
            DashboardMainFragmentDirections.actionMainFragmentToDetailsFragment((item.data as MeasurementItem).data.meterId)
        findNavController().navigate(action)
    }

}