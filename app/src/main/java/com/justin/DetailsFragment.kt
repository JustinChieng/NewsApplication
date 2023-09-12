package com.justin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.justin.newsapplication.R
import com.justin.newsapplication.databinding.FragmentDetailsBinding
import java.io.File

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val new = news[position]
        val imageUrl =new.urltoImage

        //val file = File(args.path)
        Glide
            .with(this)
            //.load(file)
            .load(url)
            .placeholder(R.drawable.ic_img)
            .into(binding.ivImage)
    }

}