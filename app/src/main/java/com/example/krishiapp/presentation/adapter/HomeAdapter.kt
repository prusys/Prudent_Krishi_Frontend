package com.example.prusys.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.krishiapp.databinding.ItemsBinding
import com.example.krishiapp.domain.Info
import com.example.krishiapp.presentation.detail.DetailActivity

class HomeAdapter(val context: Context,val info:List<Info>):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    class HomeViewHolder(val binding: ItemsBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(ItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val data=info[position]
        holder.binding.name.text=data.name
        holder.binding.temp.text=data.degree
        Glide.with(context)
            .load(data.image)
            .into(holder.binding.imageView)
        holder.itemView.setOnClickListener {
           val intent=Intent(context, DetailActivity::class.java)
            intent.putExtra("name",data.name)
            context.startActivity(intent)
        }

    }
    override fun getItemCount(): Int {
     return 6
    }
}