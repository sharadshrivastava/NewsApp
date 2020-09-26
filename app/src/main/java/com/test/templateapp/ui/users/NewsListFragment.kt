package com.test.templateapp.ui.users

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
import com.test.templateapp.R
import com.test.templateapp.data.network.Resource.Status.*
import com.test.templateapp.databinding.FragmentListBinding
import com.test.templateapp.ui.common.showErrorBar
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
        vm.layoutIds = arrayOf(R.layout.layout_news_item_latest, R.layout.layout_news_item)
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
                SUCCESS -> binding.isLoading = false
                ERROR -> {
                    binding.isLoading = false
                    showErrorBar(it.message)
                }
            }
        }
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