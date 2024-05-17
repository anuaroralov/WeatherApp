package com.example.weatherapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ItemDayWeatherBinding
import com.example.weatherapp.domain.model.ForecastDay

class DayWeatherListAdapter(private val context: Context) :
    ListAdapter<ForecastDay, DayWeatherListAdapter.DayWeatherViewHolder>(DayWeatherDiffCallback) {

    class DayWeatherViewHolder(
        val binding: ItemDayWeatherBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayWeatherViewHolder {
        val binding = ItemDayWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DayWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayWeatherViewHolder, position: Int) {
        val fDay = getItem(position)
        with(holder.binding) {
            with(fDay) {
                textView.text=date.toString().substring(5)
                textView2.text=day?.condition?.text
                Glide.with(context)
                    .load(day?.condition?.icon)
                    .into(imageView)
                textView3.text=String.format("%.1f°C-%.1f°C",day?.minTempC,day?.maxTempC)
            }
        }
    }

    object DayWeatherDiffCallback : DiffUtil.ItemCallback<ForecastDay>() {

        override fun areItemsTheSame(oldItem: ForecastDay, newItem: ForecastDay): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: ForecastDay, newItem: ForecastDay): Boolean {
            return oldItem == newItem
        }
    }
}