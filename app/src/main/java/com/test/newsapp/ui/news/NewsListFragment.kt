package com.test.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.test.newsapp.R
import com.test.newsapp.data.network.Resource.Status.*
import com.test.newsapp.databinding.FragmentListBinding
import com.test.newsapp.ui.common.showErrorBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private val vm: NewsViewModel by viewModels()
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false).apply {
            binding = this
            lifecycleOwner = this@NewsListFragment
            vm = this@NewsListFragment.vm
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDivider()
        observeFeeds()
        handleItemClick()
        refresh.setOnRefreshListener {
            observeFeeds()
        }
    }

    private fun setupDivider() {
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let { itemDecorator.setDrawable(it) }
        rvList.addItemDecoration(itemDecorator)
    }

    private fun observeFeeds() {
        vm.feeds.observe(viewLifecycleOwner) {
            when (it.status) {
                LOADING -> binding.isLoading = true
                SUCCESS -> handleResponse()
                ERROR -> handleResponse(false, it.message)
            }
        }
    }

    private fun handleResponse(isSuccess: Boolean = true, msg: String? = null) {
        binding.isLoading = false
        if (refresh.isRefreshing) refresh.isRefreshing = false
        if (!isSuccess) showErrorBar(msg)
    }

    private fun handleItemClick() {
        vm.clickListener.observe(viewLifecycleOwner) {
            if (it != null) {
                view?.findNavController()
                    ?.navigate(NewsListFragmentDirections.listFragmentAction(it))
                vm.clickListener.value = null
            }
        }
    }
}