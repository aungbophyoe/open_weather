package com.aungbophyoe.space.openweather.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aungbophyoe.space.openweather.R
import com.aungbophyoe.space.openweather.adapter.DailyWeatherRecyclerAdapter
import com.aungbophyoe.space.openweather.databinding.FragmentHomeBinding
import com.aungbophyoe.space.openweather.utils.Utility
import com.aungbophyoe.space.openweather.viewmodels.HomeViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding : FragmentHomeBinding? = null
    private val navController : NavController by lazy {
        findNavController()
    }

    private val homeViewModel : HomeViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val dailyWeatherRecyclerAdapter by lazy {
        DailyWeatherRecyclerAdapter(requireContext())
    }

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
            rvDaily.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            ivSearch.setOnClickListener {
                navController.navigate(R.id.viewSearch)
            }
            lifecycleOwner = this@HomeFragment
            viewModel = homeViewModel
            rvDaily.adapter = dailyWeatherRecyclerAdapter
            Dexter.withContext(activity)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        if (ActivityCompat.checkSelfPermission(
                                requireContext(),
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                requireContext(),
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            return
                        }
                        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
                        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                            Log.d("locationA","${location?.latitude.toString()} : ${location?.longitude.toString()}")
                            homeViewModel.setLocation(location?.latitude.toString(),location?.longitude.toString())
                            homeViewModel.getWeather()
                        }
                        val locationManager = this@HomeFragment.activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                        val location =locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                        if(location!=null){
                            homeViewModel.setLocation(location?.latitude.toString(),location?.longitude.toString())
                            Log.d("location","${location?.latitude.toString()} : ${location?.longitude.toString()}")
                            homeViewModel.getWeather()
                        }
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {

                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: PermissionRequest?,
                        p1: PermissionToken?
                    ) {

                    }

                })
                .check()
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onResume() {
        super.onResume()
    }

    companion object {

    }
}