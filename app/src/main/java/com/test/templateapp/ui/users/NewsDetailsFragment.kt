package com.test.templateapp.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.test.templateapp.data.common.INVALID
import com.test.templateapp.data.network.Resource
import com.test.templateapp.databinding.FragmentDetailsBinding
import com.test.templateapp.ui.common.showErrorBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {

    private val vm: NewsViewModel by viewModels()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args by navArgs<NewsDetailsFragmentArgs>()
        if (args.id != INVALID) {
            observeUsers(args.id)
        }
    }

    private fun observeUsers(id : Int) {
        vm.feeds.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.isLoading = false
                    //message.text = it.data?.title
                }
                Resource.Status.ERROR -> {
                    binding.isLoading = false
                    showErrorBar(it.message)
                }
                Resource.Status.LOADING -> binding.isLoading = true
            }
        }
    }
}