package com.example.weatherapp.presentation

import android.app.SearchManager
import android.content.Context
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import androidx.navigation.fragment.findNavController
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.FragmentWeatherListBinding
import javax.inject.Inject


class WeatherListFragment : Fragment() {
    private var _binding: FragmentWeatherListBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentWeatherListBinding is null")

    private lateinit var adapter: MyListAdapter
    private lateinit var searchAdapter: SimpleCursorAdapter

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
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refreshList()

        setupRecyclerView()
        setupSearchView()
        setupSwipeRefresh()

        observeViewModel()

        binding.button.setOnClickListener {
            viewModel.refreshList()
        }
    }

    private fun setupRecyclerView() {
        adapter = MyListAdapter(requireContext()) {
            launchDetailFragment(it.location?.name ?: "")
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        setupSwipeListener(binding.recyclerView)
    }

    private fun setupSearchView() {
        searchAdapter = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            null,
            arrayOf("suggestion"),
            intArrayOf(android.R.id.text1),
            0
        )
        val searchManager = requireContext().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            suggestionsAdapter = searchAdapter
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    launchDetailFragment(query.orEmpty())
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.clearSuggestions()
                    newText?.let { viewModel.getCurrentWeather(it) }
                    return true
                }
            })
            setOnSuggestionListener(object : SearchView.OnSuggestionListener {
                override fun onSuggestionSelect(position: Int) = false

                override fun onSuggestionClick(position: Int): Boolean {
                    val cursor = searchAdapter.cursor
                    cursor.moveToPosition(position)
                    val columnIndex = cursor.getColumnIndex("suggestion")
                    if (columnIndex >= 0) {
                        val suggestion = cursor.getString(columnIndex)
                        binding.searchView.setQuery(suggestion, true)
                    } else {
                        Toast.makeText(requireContext(), "Suggestion not found", Toast.LENGTH_SHORT).show()
                    }
                    return true
                }
            })
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshList()
        }
    }

    private fun observeViewModel() {
        viewModel.listOfWeather.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.swipeRefreshLayout.isRefreshing = isLoading
        }

        viewModel.errorMessages.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage.isNotEmpty()) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                viewModel.clearErrorMessage()
            }
        }

        viewModel.suggestions.observe(viewLifecycleOwner) { suggestions ->
            val cursor = MatrixCursor(arrayOf(BaseColumns._ID, "suggestion"))
            suggestions.forEachIndexed { index, suggestion ->
                cursor.addRow(arrayOf(index, suggestion))
            }
            searchAdapter.changeCursor(cursor)
        }

        viewModel.internetConnection.observe(viewLifecycleOwner) { isConnected ->
            if (!isConnected) {
                binding.constraintLayout.visibility = View.GONE
                binding.button.visibility = View.VISIBLE
            } else {
                binding.constraintLayout.visibility = View.VISIBLE
                binding.button.visibility = View.GONE
            }
        }
    }

    private fun setupSwipeListener(rvShopList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteFromList(item.location?.name.orEmpty()).invokeOnCompletion {
                    viewModel.refreshList()
                }
            }
        }
        ItemTouchHelper(callback).attachToRecyclerView(rvShopList)
    }

    private fun launchDetailFragment(name: String) {
        if (name.isNotEmpty()) {
            findNavController().navigate(WeatherListFragmentDirections.actionWeatherListFragmentToWeatherDetailFragment(name))
        }
    }
}
