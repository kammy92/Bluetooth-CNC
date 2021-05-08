package com.karman.bluetoothcnc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.karman.bluetoothcnc.R
import com.karman.bluetoothcnc.databinding.ListItemOperationBinding
import com.karman.bluetoothcnc.listener.OperationItemClickListener
import com.karman.bluetoothcnc.room.Operation

/**
 * This Adapter is used to handle the view implementation for
 * Saved Payment Cards in Profile screen
 *
 * @author Karman Singh on April 22, 2021
 */
class OperationAdapter(var operationList: List<Operation>,
        var operationItemClickListener: OperationItemClickListener)
    : RecyclerView.Adapter<OperationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ListItemOperationBinding>(
                LayoutInflater.from(parent.context),
                R.layout.list_item_operation, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = operationList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, operationList[position])
    }

    inner class ViewHolder(private val binding: ListItemOperationBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, operation: Operation) {
            binding.operation = operation
            binding.operationItemClickListener = operationItemClickListener
            binding.position = position
            binding.executePendingBindings()
        }
    }
}