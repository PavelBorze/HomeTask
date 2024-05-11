package com.pavelb.mishlohatest.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.pavelb.mishlohatest.R
import com.pavelb.mishlohatest.databinding.FragRepoBinding
import com.pavelb.mishlohatest.presentation.viewmodel.RepoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RepoFragment : Fragment() {

    private lateinit var _binding: FragRepoBinding
    private val args: RepoFragmentArgs by navArgs()
    private val viewModel: RepoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragRepoBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(_binding) {
            viewModel.checkIfInFavorites(args.repo.id)
            Glide.with(avatar)
                .load(args.repo.avatarUrl)
                .fitCenter()
                .error(android.R.drawable.stat_notify_error)
                .into(avatar)
            username.text = args.repo.ownerName
            language.text = args.repo.language
            description.text = args.repo.description
            createDate.text = args.repo.createDate  //in production we would parse the Date object and then display it as localized date
            updateDate.text = args.repo.updateDate  //in production we would parse the Date object and then display it as localized date
            issuesOpened.text = args.repo.openIssues
            stars.text = args.repo.stargazersCount
            forks.text = args.repo.forks
            watchers.text = args.repo.watchers

            btnBrowse.setOnClickListener {
                try {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(args.repo.url))
                    startActivity(browserIntent)
                } catch (e: Exception){
                    e.printStackTrace()
                    Toast.makeText(requireContext(),
                        getString(R.string.could_not_open_the_browser),
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }

            btnFavorite.setOnClickListener {
                viewModel.onAddRepoClicked(args.repo)
            }
            viewModel.isInFavorites.observe(viewLifecycleOwner) {
                btnFavorite.isEnabled = !it
            }


        }
    }


}