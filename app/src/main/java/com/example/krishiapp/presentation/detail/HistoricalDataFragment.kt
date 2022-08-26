package com.example.krishiapp.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krishiapp.R
import com.example.krishiapp.databinding.FragmentHistoricalDataBinding
import com.example.krishiapp.domain.SensorDetails
import com.example.prusys.adapter.SensorDeviceAdapter

class HistoricalDataFragment : Fragment() {
    lateinit var binding: FragmentHistoricalDataBinding
    lateinit var sensorDeviceAdapter: SensorDeviceAdapter
    var details:ArrayList<SensorDetails> = ArrayList()
    var detail:ArrayList<SensorDetails> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_historical_data,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        details.addAll(listOf(
            SensorDetails("Soil Moisture"),
            SensorDetails("Temperature"),
            SensorDetails("Humidity"),
            SensorDetails("Rainfall"),
            SensorDetails("Light-PAR & Solar Radiation"),
            SensorDetails("Wind Speed"),
            SensorDetails("Evaporation"),
            SensorDetails("LeafWetness"),
            SensorDetails("Barometric Pressure"),
            SensorDetails("Water Level"),
            SensorDetails("PH-Value"),
        ))
        detail.addAll(listOf(
            SensorDetails("Table"),
            SensorDetails("Graph"),
            SensorDetails("Bar Chart"),
            SensorDetails("Pie-Chart"),

        ))
        setRv()
    }
    private fun setRv() {
        binding.recyclerView.apply {
            layoutManager= LinearLayoutManager(requireContext())
            sensorDeviceAdapter= SensorDeviceAdapter(requireContext(),details)
            adapter=sensorDeviceAdapter
        }
        binding.dataComparisionRv.apply {
            layoutManager= LinearLayoutManager(requireContext())
            sensorDeviceAdapter= SensorDeviceAdapter(requireContext(),detail)
            adapter=sensorDeviceAdapter
        }
    }
}
