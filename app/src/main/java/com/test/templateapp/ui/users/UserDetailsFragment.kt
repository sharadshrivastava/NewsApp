package com.test.templateapp.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.test.templateapp.data.common.INVALID
import com.test.templateapp.data.network.Resource
import com.test.templateapp.databinding.FragmentDetailsBinding
import com.test.templateapp.databinding.FragmentListBinding
import com.test.templateapp.ui.common.showErrorBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.*

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private val vm: UserDetailsViewModel by viewModels()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args by navArgs<UserDetailsFragmentArgs>()
        if (args.id != INVALID) {
            observeUsers(args.id)
        }
    }

    private fun observeUsers(id : Int) {
        vm.userDetails(id).observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.isLoading = false
                    message.text = it.data?.title
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