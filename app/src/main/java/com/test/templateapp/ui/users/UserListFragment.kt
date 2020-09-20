package com.test.templateapp.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.test.templateapp.data.network.Resource.Status.*
import com.test.templateapp.databinding.FragmentListBinding
import com.test.templateapp.ui.common.showErrorBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val vm: UserListViewModel by viewModels()
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        submit.setOnClickListener {
            view.findNavController().navigate(UserListFragmentDirections.listFragmentAction(1))
        }
        observeUsers()
    }

    private fun observeUsers() {
        vm.users().observe(viewLifecycleOwner) {
            when (it.status) {
                SUCCESS -> {
                    binding.isLoading = false
                    Toast.makeText(requireContext(), it.data?.size.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                ERROR -> {
                    binding.isLoading = false
                    showErrorBar(it.message)
                }
                LOADING -> binding.isLoading = true
            }
        }
    }
}