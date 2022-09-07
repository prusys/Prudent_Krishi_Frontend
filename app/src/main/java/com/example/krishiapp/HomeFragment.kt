package com.example.krishiapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.krishiapp.databinding.FragmentHomeBinding
import com.example.krishiapp.domain.Info
import com.example.prusys.adapter.HomeAdapter
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
   private lateinit var homeAdapter: HomeAdapter
   private var info: ArrayList<Info> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        info.addAll(listOf(Info("27\nM/KG",R.drawable.moisture,"Soil Moisture"),
            Info("29°C",R.drawable.temperature,"Temperature"),
            Info("87%",R.drawable.hygrometer,"Humidity"),
            Info("20°C",R.drawable.torrential_rain,"Rainfall"),
            Info("100\nnm",R.drawable.rain_light_sensor,"Light_PAR"),
            Info("17\nKM/H",R.drawable.wind,"Speed")
        ))
        setRv()
    }

    private fun setRv() {
        binding.rv.apply {
            layoutManager= GridLayoutManager(requireContext(),2)
            homeAdapter= HomeAdapter(requireContext(),info)
            adapter=homeAdapter
        }
    }
}