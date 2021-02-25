package com.coroutineslogapp.ui.repositories

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.coroutineslogapp.R
import com.coroutineslogapp.domain.model.TypeOfRepositoryList
import com.coroutineslogapp.ui.repositories.adapter.ItemDecorator
import com.coroutineslogapp.ui.repositories.adapter.RepositoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_repositories.*
import kotlinx.android.synthetic.main.fragment_repositories.repositories_swipe_container as swipeContainer
import kotlinx.android.synthetic.main.fragment_repositories.repositories_container as repositoriesContainer
import kotlinx.android.synthetic.main.fragment_repositories.repositories_toolbar as repositoriesToolbar

@AndroidEntryPoint
class RepositoriesFragment : Fragment() {
    private val viewModel: RepositoriesViewModel by viewModels()
    private val repositoriesAdapter = RepositoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repositories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(repositoriesContainer) {
            repositoriesContainer.adapter = repositoriesAdapter
            addItemDecoration(ItemDecorator())
        }

        viewModel.loadUserRepositories(typeOfRepositoryList = TypeOfRepositoryList.ALL)
        setObservers()
        setListeners()
    }

    private fun setListeners() {
        swipeContainer.setOnRefreshListener {
            viewModel.loadUserRepositories(typeOfRepositoryList = TypeOfRepositoryList.ALL)
            swipeContainer.isRefreshing = false
        }
        repositoriesToolbar.setOnMenuItemClickListener { menuItem ->
            if (menuItem.itemId == R.id.action_settings) {
                findNavController().navigate(R.id.RepositorySettingsFragment)
                true
            } else {
                false
            }
        }

        repositoriesToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            findNavController().navigate(R.id.RepositorySettingsFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setObservers() {
        viewModel.repositoriesLiveData.observe(requireActivity(), { repositories ->
            if (!repositories.isNullOrEmpty()) {
                repositoriesAdapter.submitList(repositories)
            }
        })
        repositoriesAdapter.itemDetailsNavigator.observe(requireActivity(), { repositoryId ->
            val action =
                RepositoriesFragmentDirections.actionRepositoriesFragmentToRepositoryDetailsFragment(
                    repositoryId
                )
            findNavController().navigate(action)
        })
    }
}
