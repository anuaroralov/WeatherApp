package com.example.weatherapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ItemWeatherBinding
import com.example.weatherapp.domain.model.CurrentWeather

class MyListAdapter(private val context: Context, private val onClickListener: (CurrentWeather) -> Unit) :
    ListAdapter<CurrentWeather, MyListAdapter.WeatherViewHolder>(CatDiffCallback) {

    class WeatherViewHolder(
        val binding: ItemWeatherBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentWeather = getItem(position)
        with(holder.binding) {
            with(currentWeather) {
                tvName.text = currentWeather.location?.name
                tvTime.text =currentWeather.current?.lastUpdated
                tvCondition.text= currentWeather.current?.condition?.text
                tvTemperature.text=currentWeather.current?.tempC.toString()
                tvFeelsLike.text=currentWeather.current?.feelsLikeC.toString()
                Glide.with(context)
                    .load(currentWeather.current?.condition?.icon)
                    .fitCenter()
                    .into(imageView)


                root.setOnClickListener {

                }
            }
        }
    }

    object CatDiffCallback : DiffUtil.ItemCallback<CurrentWeather>() {

        override fun areItemsTheSame(oldItem: CurrentWeather, newItem: CurrentWeather): Boolean {
            return oldItem.location?.name == newItem.location?.name
        }

        override fun areContentsTheSame(oldItem: CurrentWeather, newItem: CurrentWeather): Boolean {
            return oldItem == newItem
        }
    }
}
