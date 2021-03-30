package com.exampl.gwftest.presentation.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.exampl.gwftest.R
import com.exampl.gwftest.databinding.ActivityDashboardBinding
import com.exampl.gwftest.presentation.login.LoginActivity
import com.exampl.gwftest.util.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DashboardActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val viewModel: DashboardViewModel by lazy {
        ViewModelProvider(
            this@DashboardActivity,
            viewModelProviderFactory
        ).get(DashboardViewModel::class.java)
    }

    private val binding: ActivityDashboardBinding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }

    private lateinit var navController: NavController

    private var searchItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initToolbar(binding.toolbarHolder.appBar)
        initNavigation()
        observe()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dashboard, menu)
        menu?.let {
            searchItem = menu.findItem(R.id.search)
            initSearchView()
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            R.id.sign_out -> {
                viewModel.signOut()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar(toolbar: Toolbar?) {
        setSupportActionBar(toolbar)
        showBackButton(false)
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.dashboardNavHost) as NavHostFragment

        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            showBackButton(destination.id != R.id.mainFragment)
            showSearchButton(destination.id == R.id.mainFragment)
        }

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    private fun observe() {
        viewModel.accessTokenData().observe(this) {
            if (it == null) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun showBackButton(visible: Boolean) {
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(visible)
            setHomeButtonEnabled(visible)
            setDisplayHomeAsUpEnabled(visible)
        }
    }

    private fun showSearchButton(visible: Boolean) {
        searchItem?.isVisible = visible
    }

    private fun initSearchView() {
        val searchView = searchItem?.actionView as SearchView

        searchView.queryHint = getString(R.string.type_search_meter)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("TAG", "onQueryTextChange: $newText")
                viewModel.setSearchText(newText)
                return true
            }

        })
    }
}