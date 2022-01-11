package com.aungbophyoe.space.openweather.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.aungbophyoe.space.openweather.R
import com.aungbophyoe.space.openweather.databinding.FragmentSearchBinding
import com.aungbophyoe.space.openweather.utils.Utility
import com.aungbophyoe.space.openweather.viewmodels.HomeViewModel
import com.aungbophyoe.space.openweather.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var binding : FragmentSearchBinding? = null
    private val navController : NavController by lazy {
        findNavController()
    }

    private val searchViewModel : SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false)
        val view = binding!!.root
        binding!!.apply {
            backBtn.setOnClickListener {
                navController.popBackStack()
            }

            edtSearch.setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Utility.hideSoftKeyboard(requireActivity())
                    val cityName = edtSearch.text.toString()
                    searchViewModel.getWeatherByCity(cityName)
                    true
                } else false
            }

            ivSearch.setOnClickListener {
                Utility.hideSoftKeyboard(requireActivity())
                val cityName = edtSearch.text.toString()
                searchViewModel.getWeatherByCity(cityName)
            }


         lifecycleOwner = this@SearchFragment
         viewModel = searchViewModel
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}