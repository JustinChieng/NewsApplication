package com.justin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.justin.MainActivity
import com.justin.newsapplication.R
import com.justin.newsapplication.databinding.FragmentDetailsBinding
import com.justin.newsapplication.databinding.FragmentHomeBinding
import com.justin.ui.fragments.DetailsFragmentArgs

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).hideToolbar()

        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.newsFeedFragment4)
        }
    }
}