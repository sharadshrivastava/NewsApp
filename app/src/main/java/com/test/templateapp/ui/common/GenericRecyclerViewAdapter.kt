package com.test.templateapp.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.test.templateapp.BR

/*@BindingAdapter(value = ["items", "itemLayout"], requireAll = true)
fun <T> configureRecyclerView(recyclerView: RecyclerView, items: List<Any>?, layoutIDs: List<Int>){
    if(recyclerView.adapter == null){
        recyclerView.adapter = GenericRecyclerViewAdapter(items, layoutIDs, null)
    }else{
        (recyclerView.adapter as GenericRecyclerViewAdapter).setItems(items)
    }
}*/

@BindingAdapter(value = ["items", "itemLayout"], requireAll = true)
fun <T> configureRecyclerView(recyclerView: RecyclerView, items: List<Any>?, layoutIDs: Int){
    if(recyclerView.adapter == null){
        recyclerView.adapter = GenericRecyclerViewAdapter(items, listOf(layoutIDs), null)
    }else{
        (recyclerView.adapter as GenericRecyclerViewAdapter).setItems(items)
    }
}

class GenericRecyclerViewAdapter(
    var list: List<Any>?,
    private val layoutIDs: List<Int>,
    private val listener: OnItemClickListener?
) : RecyclerView.Adapter<GenericRecyclerViewAdapter.GenericViewHolder<Any>>() {

    private lateinit var binding: ViewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<Any> {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, layoutIDs[0], parent, false)
        return GenericViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<Any>, position: Int) {
        holder.bind(list?.getOrNull(position))
    }

    override fun getItemCount(): Int {
        return list.orEmpty().size
    }

    override fun getItemViewType(position: Int): Int {
        return layoutIDs.getOrElse(position) { 0 }
    }

    fun setItems(list: List<Any>?) {
        this.list = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(id: Int?)
    }

    class GenericViewHolder<T>(
        private val binding: ViewDataBinding, private val listener: OnItemClickListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T?) {
            binding.setVariable(BR.item, item)

            itemView.setOnClickListener {
                //listener.onItemClick(item.id)
            }
        }
    }
}