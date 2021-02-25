package com.coroutineslogapp.ui.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.coroutineslogapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_repository_details.*
import kotlinx.android.synthetic.main.fragment_repository_details.repo_visibility as repoVisibility

@AndroidEntryPoint
class RepositoryDetailsFragment : Fragment() {
    private val args: RepositoryDetailsFragmentArgs by navArgs()
    private val viewModel: RepositoryDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRepositoryDetails(args.repositoryId)
        setObservers()
    }

    private fun setObservers() {
        viewModel.repositoryDetails.observe(requireActivity(), { repoDetails ->
            description.text = if (repoDetails.description.isEmpty()) {
                getString(R.string.no_description)
            } else {
                repoDetails.description
            }
            repoVisibility.text =
                if (repoDetails.isPrivate) getString(R.string.visibility_private) else getString(R.string.visibility_public)
        })
    }
}
