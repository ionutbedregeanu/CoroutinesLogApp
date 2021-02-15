package com.coroutineslogapp.ui.login

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.coroutineslogapp.R
import com.coroutineslogapp.connectivity.ConnectivityReceiver
import com.coroutineslogapp.connectivity.ConnectivityReceiverListener
import com.coroutineslogapp.ui.util.NetworkUtil
import com.coroutineslogapp.ui.util.showErrorDialog
import com.coroutineslogapp.ui.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_login.login_button as loginButton
import kotlinx.android.synthetic.main.fragment_login.login_main_container as loginMainContainer
import kotlinx.android.synthetic.main.fragment_login.username_container as usernameContainer
import kotlinx.android.synthetic.main.fragment_login.token_container as tokenContainer

private const val TOKEN_MIN_LENGTH = 40

@AndroidEntryPoint
class LoginFragment : Fragment(), ConnectivityReceiverListener {
    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var network: NetworkUtil
    private lateinit var connectivityReceiver: ConnectivityReceiver

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectivityReceiver = ConnectivityReceiver(this, network)
        setListeners()
        setWatchers()
        setObservers()
    }

    override fun onStart() {
        super.onStart()
        requireContext().registerReceiver(
            connectivityReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(connectivityReceiver)
    }

    private fun setObservers() {
        loginViewModel.shouldNavigateToNextScreen.observe(requireActivity(), { shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
            } else {
                showErrorDialog(getString(R.string.try_again_later))
            }
        })
    }

    private fun setListeners() {
        loginButton.setOnClickListener {
            setUsernameError(username.text)
            setTokenError(token.text)
            if (usernameContainer.error.isNullOrEmpty() && tokenContainer.error.isNullOrEmpty()) {
                loginViewModel.loginUser(
                    username = username.text.toString(),
                    accessToken = token.text.toString()
                )
            }
        }
    }

    private fun setWatchers() {
        username.addTextChangedListener { text: Editable? ->
            setUsernameError(text)
        }

        token.addTextChangedListener { text: Editable? ->
            setTokenError(text)
        }
    }

    private fun setTokenError(text: Editable?) {
        tokenContainer.error =
            if (text.isNullOrEmpty() || text.length < TOKEN_MIN_LENGTH) {
                getString(R.string.invalid_token)
            } else {
                null
            }
    }

    private fun setUsernameError(text: Editable?) {
        usernameContainer.error = if (text.isNullOrEmpty()) {
            getString(R.string.invalid_username)
        } else {
            null
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
            showSnackbar(loginMainContainer, R.string.network_connection_established, R.color.teal_700)
            setLoginButtonStatus(true)
        } else {
            showSnackbar(loginMainContainer, R.string.unavailable_network_connection, R.color.red)
            setLoginButtonStatus(false)
        }
    }

    private fun setLoginButtonStatus(status: Boolean) {
        if (status) {
            loginButton.alpha = 1f
        } else {
            loginButton.alpha = 0.5f
        }
        loginButton.isEnabled = status
    }
}
