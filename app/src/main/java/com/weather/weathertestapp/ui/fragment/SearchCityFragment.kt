package com.weather.weathertestapp.ui.fragment

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.weather.weathertestapp.App.Companion.appComponent
import com.weather.weathertestapp.R
import com.weather.weathertestapp.databinding.BottomSheetSearchFragmentBinding
import com.weather.weathertestapp.ui.adapter.SearchCitiesRVAdapter
import com.weather.weathertestapp.ui.viewmodel.SearchCityViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject


class SearchCityFragment : BottomSheetDialogFragment()  {

    @Inject
    lateinit var appContext: Context

    @Inject
    lateinit var searchCityViewModel: SearchCityViewModel

    lateinit var searchCitiesRVAdapter: SearchCitiesRVAdapter

    lateinit var binding: BottomSheetSearchFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return BottomSheetSearchFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            setupBottomSheet(it)
        }
        return dialog
    }

    private fun setupBottomSheet(dialogInterface: DialogInterface) {
        val bottomSheetDialog = dialogInterface as BottomSheetDialog
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) ?: return
        bottomSheet.setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = BottomSheetSearchFragmentBinding.bind(view)

        setupRecyclerView()

        binding.searchField.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchCitiesRVAdapter.setQuery(newText.toLowerCase().trim())
                searchCitiesRVAdapter.notifyDataSetChanged()
                return true
            }
        })

        searchCityViewModel.getCities().observe(viewLifecycleOwner) { it->

            searchCitiesRVAdapter.setAllCitiesList(it.cities)
            searchCitiesRVAdapter.setQuery("")
            binding.cityListRv.adapter = searchCitiesRVAdapter

        }

        searchCityViewModel.getIsLoading().observe(viewLifecycleOwner) {
            if (it) {
                binding.loadingIndicator.visibility = View.VISIBLE
            } else binding.loadingIndicator.visibility = View.GONE
        }

        searchCityViewModel.getError().observe(viewLifecycleOwner) {
            Log.e("ERROR:", it)
        }

    }

    private fun setupRecyclerView() {
        binding.cityListRv.layoutManager = LinearLayoutManager(appContext)
        binding.cityListRv.setHasFixedSize(true)
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.rv_item_divider) as Drawable)
        binding.cityListRv.addItemDecoration(itemDecorator)
        searchCitiesRVAdapter = SearchCitiesRVAdapter()

    }

}


