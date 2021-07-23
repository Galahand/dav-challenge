package com.example.platzi_challenge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.platzi_challenge.R
import com.example.platzi_challenge.databinding.DetailFragmentBinding
import com.example.platzi_challenge.ui.utils.FragmentBinding

class DetailFragment : Fragment() {
    private val binding: DetailFragmentBinding by FragmentBinding(R.layout.detail_fragment)
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.beer = args.beer
        binding.backB.setOnClickListener { findNavController().popBackStack() }
        return binding.root
    }
}