package com.example.weatherapp.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.FragmentWeatherDetailBinding
import com.example.weatherapp.domain.model.ForecastWeather
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherDetailFragment : Fragment() {

    private val args by navArgs<WeatherDetailFragmentArgs>()
    private var _binding: FragmentWeatherDetailBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentWeatherDetailBinding is null")

    @Inject
    lateinit var viewModelFactory: WeatherViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[WeatherViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as MyApplication).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.contentContainer.visibility = View.GONE
                binding.floatingActionButton2.visibility=View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.contentContainer.visibility = View.VISIBLE
                binding.floatingActionButton2.visibility=View.VISIBLE
            }
        }

        lifecycleScope.launch {
            try {
                val forecastWeather = viewModel.getWeatherForecast(args.location)
                bindWeatherData(forecastWeather)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun bindWeatherData(forecastWeather: ForecastWeather) {
        with(binding) {

            floatingActionButton2.setOnClickListener {
                viewModel.addToList(forecastWeather.location?.name ?: "")
            }

            temperature.text = forecastWeather.current?.tempC.toString()+"°"
            location.text = forecastWeather.location?.name
            weatherDescription.text = forecastWeather.current?.condition?.text
            Glide.with(requireContext())
                .load(forecastWeather.current?.condition?.icon)
                .into(imageView2)
            highLowTemp.text = forecastWeather.current?.feelsLikeC.toString()+"°"

            val hourAdapter = HourWeatherListAdapter(requireContext())
            hourRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            hourRecyclerView.adapter = hourAdapter
            hourAdapter.submitList(forecastWeather.forecast?.forecastDay?.get(0)?.hour)

            val dayAdapter = DayWeatherListAdapter(requireContext())
            dayRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            dayRecyclerView.adapter = dayAdapter
            dayAdapter.submitList(forecastWeather.forecast?.forecastDay)
        }
    }
}
