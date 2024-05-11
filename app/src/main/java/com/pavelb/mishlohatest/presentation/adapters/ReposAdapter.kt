package com.pavelb.mishlohatest.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pavelb.mishlohatest.data.entities.Repository
import com.pavelb.mishlohatest.databinding.ItemRepoBinding
import com.pavelb.mishlohatest.presentation.fragments.HomeFragmentDirections

class ReposAdapter: PagingDataAdapter<Repository, ReposAdapter.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { repo ->
            with(holder) {
                itemView.tag = repo
                if (repo != null) {
                    bind(createOnClickListener(binding, repo), repo)
                }
            }
        }
    }

    private fun createOnClickListener(binding : ItemRepoBinding, repo: Repository): View.OnClickListener {
        return View.OnClickListener {
           val directions = HomeFragmentDirections.actionHomeFragmentToRepoFragment(repo)

            it.findNavController().navigate(directions)
        }
    }

    class ViewHolder(val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, repo: Repository) {

            binding.apply {

                //Glide has its own caching strategy, so it will load the image from the cache if it's already there
                //Their implementation is better than creating a db table of url string and image byte array or local image uri
                Glide.with(itemView)
                    .load(repo.avatarUrl)
                    .fitCenter()
                    .error(android.R.drawable.stat_notify_error)
                    .into(avatar)

                name.text = "${repo.ownerName}/${repo.name}"

                description.text = repo.description

                language.text = repo.language

                stars.text = repo.stargazersCount

                ViewCompat.setTransitionName(this.avatar, "avatar_${repo.id}")

                root.setOnClickListener(listener)
            }

        }
    }
}