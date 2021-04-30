package com.karman.bluetoothcnc.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.karman.bluetoothcnc.BR

class BaseAdapter<T> constructor(@LayoutRes private val layoutResourceId: Int,
        private val itemList: List<T>, private val itemClickListener: BaseItemClickListener<T>)
    : RecyclerView.Adapter<BaseAdapter<T>.Holder>() {

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup,
            viewType: Int): Holder {
        return Holder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                layoutResourceId, parent, false) as ViewDataBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position, itemList[position])
    }

    inner class Holder(private val binding: ViewDataBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, item: T) {
            binding.setVariable(BR.position, position)
            binding.setVariable(BR.item, item)
            binding.setVariable(BR.itemClickListener, itemClickListener)
        }
    }
}