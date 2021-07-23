package com.example.platzi_challenge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.platzi_challenge.R
import com.example.platzi_challenge.databinding.OverviewFragmentBinding
import com.example.platzi_challenge.ui.adapter.BeerAdapter
import com.example.platzi_challenge.ui.adapter.GridSpacingItemDecoration
import com.example.platzi_challenge.ui.utils.FragmentBinding
import com.example.platzi_challenge.ui.viewmodel.OverviewViewModel
import com.example.platzi_challenge.ui.viewmodel.RequestStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()
    private val binding: OverviewFragmentBinding by FragmentBinding(R.layout.overview_fragment)

    private val adapter by lazy { BeerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureRv()
        viewModel.fetchBeer()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listenLiveData()
        return binding.root
    }

    private fun configureRv() = binding.apply {
        beersRv.layoutManager = GridLayoutManager(requireContext(), 2)
        beersRv.addItemDecoration(GridSpacingItemDecoration(resources.getDimensionPixelSize(R.dimen.standard_spacing)))
        beersRv.adapter = adapter
        adapter.setOnTap {
            val action = OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it)
            requireView().findNavController().navigate(action)
        }
        adapter.setOnLastVisible {
            viewModel.fetchBeer()
        }
    }

    private fun listenLiveData() {
        viewModel.beers.observe(viewLifecycleOwner, adapter::addItems)
        viewModel.requestStatus.observe(viewLifecycleOwner, ::handleRequestStatus)
        viewModel.fetchBeer()
    }

    private fun handleRequestStatus(status: RequestStatus) {
        when (status) {
            is RequestStatus.Error -> Unit // TODO: Handle error
            is RequestStatus.Loading -> Unit // TODO: Handle loading
            else -> Unit
        }
    }
}