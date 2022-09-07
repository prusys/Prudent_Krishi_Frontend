package com.example.prusys.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.krishiapp.databinding.HistoricalDataBinding
import com.example.krishiapp.databinding.ItemsBinding
import com.example.krishiapp.domain.Info
import com.example.krishiapp.domain.SensorDetails
import com.example.krishiapp.presentation.detail.DetailActivity

class SensorDeviceAdapter(val context: Context,val detail:List<SensorDetails>):RecyclerView.Adapter<SensorDeviceAdapter.HistoricalData>() {
    class HistoricalData(val binding: HistoricalDataBinding):RecyclerView.ViewHolder(binding.root) {

    }
    override fun onBindViewHolder(holder: SensorDeviceAdapter.HistoricalData, position: Int) {
        val data=detail[position]
        holder.binding.sensorName.text=data.name
    }
    override fun getItemCount(): Int {
     return detail.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricalData {
        return HistoricalData(HistoricalDataBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}