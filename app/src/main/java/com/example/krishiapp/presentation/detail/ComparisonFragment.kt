package com.example.krishiapp.presentation.detail

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.krishiapp.R
import com.example.krishiapp.databinding.FragmentComparisonBinding
import java.util.*

class ComparisonFragment : Fragment() {
    lateinit var binding: FragmentComparisonBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_comparison,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.edtDate.setOnClickListener {
            val c=Calendar.getInstance()
            val year=c.get(Calendar.YEAR)
            val month=c.get(Calendar.MONTH)
            val day=c.get(Calendar.DAY_OF_MONTH)

            val dpd=DatePickerDialog(
                requireContext(),DatePickerDialog.OnDateSetListener{view,myear,monthofYear,dayofMonth->
                  binding.edtDate.setText(""+dayofMonth+"/" + monthofYear + "/" + myear)
                }, year,month,day
            )
            dpd.show()
        }
    }
}