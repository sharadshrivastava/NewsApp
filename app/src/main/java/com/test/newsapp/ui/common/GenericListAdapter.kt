package com.test.newsapp.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.test.newsapp.BR

@BindingAdapter(value = ["items", "itemLayout", "clickListener"], requireAll = true)
fun <T> configureRecyclerView(
    recyclerView: RecyclerView,
    items: List<Any>?,
    layoutIDs: Array<Int>,
    clickListener: ItemClickListener
) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = GenericRecyclerViewAdapter(items, layoutIDs, clickListener)
    } else {
        (recyclerView.adapter as GenericRecyclerViewAdapter).setItems(items)
    }
}

class GenericRecyclerViewAdapter(
    var list: List<Any>?,
    private val layoutIDs: Array<Int>,
    private val clickListener: ItemClickListener
) : RecyclerView.Adapter<GenericRecyclerViewAdapter.GenericViewHolder<Any>>() {

    private lateinit var binding: ViewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<Any> {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return GenericViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<Any>, position: Int) {
        holder.bind(list?.getOrNull(position))
    }

    override fun getItemCount(): Int {
        return list.orEmpty().size
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutIDs.size > 1) {
            if (position == 0) layoutIDs[0] else layoutIDs[1]
        } else {
            layoutIDs[0]
        }
    }

    fun setItems(list: List<Any>?) {
        this.list = list
        notifyDataSetChanged()
    }

    class GenericViewHolder<T>(
        private val binding: ViewDataBinding, private val clickListener: ItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T?) {
            binding.setVariable(BR.item, item)
            itemView.setOnClickListener {
                clickListener.onItemClick(item)
            }
        }
    }
}

interface ItemClickListener {
    fun onItemClick(item: Any?)
}