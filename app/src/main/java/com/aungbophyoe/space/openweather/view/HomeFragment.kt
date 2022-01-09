package com.aungbophyoe.space.openweather.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.aungbophyoe.space.openweather.R
import com.aungbophyoe.space.openweather.databinding.FragmentHomeBinding
import com.aungbophyoe.space.openweather.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding : FragmentHomeBinding? = null
    private val navController : NavController by lazy {
        findNavController()
    }

    private val homeViewModel : HomeViewModel by viewModels()

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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        val view = binding!!.root
        binding!!.apply {
            ivSearch.setOnClickListener {
                navController.navigate(R.id.viewSearch)
            }
            lifecycleOwner = this@HomeFragment
            viewModel = homeViewModel
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {

    }
}