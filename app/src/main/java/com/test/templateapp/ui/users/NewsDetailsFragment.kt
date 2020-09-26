package com.test.templateapp.ui.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.test.templateapp.databinding.FragmentDetailsBinding
import com.test.templateapp.databinding.LayoutNewsItemLatestBinding
import com.test.templateapp.domain.model.Item
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {

    private lateinit var binding: LayoutNewsItemLatestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutNewsItemLatestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args by navArgs<NewsDetailsFragmentArgs>()
        observeUsers(args.item)
    }

    private fun observeUsers(item: Item) {
        binding.item = item
        Log.v("sharadss", item.toString())
    }
}