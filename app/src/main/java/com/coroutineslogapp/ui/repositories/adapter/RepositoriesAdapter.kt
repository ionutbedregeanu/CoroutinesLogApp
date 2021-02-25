package com.coroutineslogapp.ui.repositories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coroutineslogapp.R
import com.coroutineslogapp.ui.model.RepositoryItem
import kotlinx.android.synthetic.main.repositories_list_item.view.*

class RepositoriesAdapter :
    ListAdapter<RepositoryItem, RepositoriesAdapter.ViewHolder>(RepositoryDiffCallback()) {
    val itemDetailsNavigator = MutableLiveData<Long>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.repositories_list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), itemDetailsNavigator)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(repository: RepositoryItem, itemDetailsNavigator: MutableLiveData<Long>) {
            itemView.name.text = repository.name
            itemView.setOnClickListener {
                itemDetailsNavigator.postValue(repository.id)
            }
        }
    }
}

class RepositoryDiffCallback : DiffUtil.ItemCallback<RepositoryItem>() {
    override fun areItemsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem) =
        oldItem == newItem
}
