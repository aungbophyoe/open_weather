package com.aungbophyoe.space.openweather.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aungbophyoe.space.openweather.databinding.RvWeatherDailyItemBinding
import com.aungbophyoe.space.openweather.network.response.OpenWeatherNetworkEntity

class DailyWeatherRecyclerAdapter constructor(private val context: Context) :
    ListAdapter<OpenWeatherNetworkEntity.Daily?, DailyWeatherRecyclerAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<OpenWeatherNetworkEntity.Daily?>(){
            override fun areItemsTheSame(
                oldItem: OpenWeatherNetworkEntity.Daily,
                newItem: OpenWeatherNetworkEntity.Daily
            ): Boolean {
                return oldItem.dateTime == newItem.dateTime
            }

            override fun areContentsTheSame(
                oldItem: OpenWeatherNetworkEntity.Daily,
                newItem: OpenWeatherNetworkEntity.Daily
            ): Boolean {
                return oldItem == newItem
            }

        }
    ) {

    class ViewHolder private constructor(private val binding: RvWeatherDailyItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: OpenWeatherNetworkEntity.Daily?){
            binding.weather = item
            binding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
                val binding = RvWeatherDailyItemBinding.inflate(layoutInflater ,parent,false)
                val viewHolder = ViewHolder(binding)
                return viewHolder
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val item = getItem(position)
            holder.bind(item)
        }catch (e:Exception){
            Log.d("recycler",e.message.toString())
        }
    }
}