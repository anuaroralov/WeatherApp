package com.example.weatherapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ItemHourWeatherBinding
import com.example.weatherapp.domain.model.Hour

class HourWeatherListAdapter(private val context: Context) :
    ListAdapter<Hour, HourWeatherListAdapter.HourWeatherViewHolder>(HourWeatherDiffCallback) {

    class HourWeatherViewHolder(
        val binding: ItemHourWeatherBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourWeatherViewHolder {
        val binding = ItemHourWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HourWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourWeatherViewHolder, position: Int) {
        val hour = getItem(position)
        with(holder.binding) {
            with(hour) {
                temp.text=hour.tempC.toString()+"°C"
                holder.binding.hour.text= hour.time.toString().substring(10)
                Glide.with(context)
                    .load(hour.condition?.icon)
                    .into(weatherIcon)
            }
        }
    }

    object HourWeatherDiffCallback : DiffUtil.ItemCallback<Hour>() {

        override fun areItemsTheSame(oldItem: Hour, newItem: Hour): Boolean {
            return oldItem.time == newItem.time
        }

        override fun areContentsTheSame(oldItem: Hour, newItem: Hour): Boolean {
            return oldItem == newItem
        }
    }
}