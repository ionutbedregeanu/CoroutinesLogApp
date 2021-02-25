package com.coroutineslogapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.coroutineslogapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_something_went_wrong.view.*
import kotlinx.android.synthetic.main.fragment_profile.error_container as errorContainer
import kotlinx.android.synthetic.main.fragment_profile.swipe_container as swipeContainer
import kotlinx.android.synthetic.main.fragment_profile.bt_contact_by_email as contactByEmailButton
import kotlinx.android.synthetic.main.fragment_profile.bt_repositories as repositoriesButton
import kotlinx.android.synthetic.main.fragment_profile.profile_details_container as profileDetailsContainer
import kotlinx.android.synthetic.main.fragment_profile.profile_toolbar as profileToolbar

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCurrentUserDetails(false)
        setObservers()
        setListeners()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setListeners() {
        errorContainer.retry.setOnClickListener {
            viewModel.getCurrentUserDetails(true)
        }

        swipeContainer.setOnRefreshListener {
            viewModel.getCurrentUserDetails(true)
            swipeContainer.isRefreshing = false
        }

        contactByEmailButton.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "message/rfc822"
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
            emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body))
            startActivity(Intent.createChooser(emailIntent, getString(R.string.email_title)))
        }

        repositoriesButton.setOnClickListener {
            findNavController().navigate(R.id.repositoriesFragment)
        }

        profileToolbar.setOnMenuItemClickListener { menuItem ->
            if (menuItem.itemId == R.id.action_logout) {
                viewModel.logoutUser()
                findNavController().navigate(R.id.loginFragment)
                true
            } else {
                false
            }
        }
    }

    private fun setObservers() {
        viewModel.userLiveData.observe(requireActivity(), { user ->
            errorContainer.isVisible = user == null
            if (user != null) {
                profileDetailsContainer.profileModel = user
            }
        })

        viewModel.loaderLiveData.observe(requireActivity(), { shouldDisplay ->
            loader.isVisible = shouldDisplay
        })
    }
}
