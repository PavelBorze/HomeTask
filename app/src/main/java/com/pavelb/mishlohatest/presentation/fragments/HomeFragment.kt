package com.pavelb.mishlohatest.presentation.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.pavelb.mishlohatest.databinding.FragHomeBinding
import com.pavelb.mishlohatest.presentation.adapters.ReposAdapter
import com.pavelb.mishlohatest.presentation.adapters.ReposLoadStateAdapter
import com.pavelb.mishlohatest.presentation.viewmodel.HomeViewModel
import com.pavelb.mishlohatest.utils.SpinnerAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var _binding: FragHomeBinding
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragHomeBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateRecyclerView()
        populateSpinner()
    }

    private fun populateRecyclerView() {
        val adapter = ReposAdapter()
        with(_binding) {
            with(recyclerView) {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                itemAnimator = DefaultItemAnimator()
                this.adapter = adapter.withLoadStateFooter(
                    footer = ReposLoadStateAdapter(retry = { adapter.retry() })
                )

            }
        }
        viewModel.repos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    private fun populateSpinner() {
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            SpinnerAction.values()
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _binding.spinner.adapter = spinnerAdapter
        _binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedAction = parent?.getItemAtPosition(position) as SpinnerAction
                viewModel.onSpinnerAction(selectedAction)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

}