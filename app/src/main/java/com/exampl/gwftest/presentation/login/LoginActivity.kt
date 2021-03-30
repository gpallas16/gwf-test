package com.exampl.gwftest.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.exampl.gwftest.R
import com.exampl.gwftest.databinding.ActivityLoginBinding
import com.exampl.gwftest.presentation.dashboard.DashboardActivity
import com.exampl.gwftest.util.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
            .apply {
                loginClickListener = this@LoginActivity
                viewModel = this@LoginActivity.viewModel
                lifecycleOwner = this@LoginActivity
            }
    }

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(
            this,
            providerFactory
        ).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.errorData.observe(this) {
            when (it) {
                LoginViewModel.Error.LOGIN_FAILED -> Snackbar.make(
                    binding.loginParent,
                    getString(R.string.wrong_email_password),
                    Snackbar.LENGTH_SHORT
                ).show()
                else -> {

                }
            }
        }
        viewModel.accessTokenData().observe(this) {
            it?.let {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            } ?: setContentView(binding.root)
        }
    }

    override fun onClick(v: View?) {
        val username = binding.loginUsernameEt.text?.toString()?.trim() ?: ""
        val password = binding.loginPasswordEt.text?.toString()?.trim() ?: ""

        if (username.isEmpty())
            binding.loginUsernameEtLayout.error = getString(R.string.enter_email)

        else
            binding.loginUsernameEtLayout.error = null

        if (password.isEmpty())
            binding.loginPasswordEtLayout.error = getString(R.string.enter_password)

        else
            binding.loginPasswordEtLayout.error = null

        if (password.isEmpty() || username.isEmpty())
            return

        viewModel.loginUser(username, password)
    }


}