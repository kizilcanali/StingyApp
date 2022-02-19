package com.alikizilcan.stingyapp.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.alikizilcan.stingyapp.databinding.FragmentHomeBinding
import com.alikizilcan.stingyapp.infra.base.BaseFragment
import com.alikizilcan.stingyapp.infra.colorsList
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var _binding: FragmentHomeBinding
    val binding get() = _binding

    override val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.categoriesData.observe(viewLifecycleOwner){

            val pieDataSet = PieDataSet(it, "label")
            println("pieDataSet $pieDataSet")
            pieDataSet.valueTextSize = 12f
            pieDataSet.colors = colorsList
            // pieDataSet.color
            val pieData = PieData(pieDataSet)
            println("pieData $pieData")
            pieData.setDrawValues(true)

            with(binding.pieChart){
                setUsePercentValues(true)
                description.text = ""
                isDrawHoleEnabled = true
                setHoleColor(Color.parseColor("#E6DDC4"))
                setTouchEnabled(true)
                setDrawEntryLabels(false)
                setExtraOffsets(0f, 0f, 0f, 50f)
                isRotationEnabled = false
                legend.orientation = Legend.LegendOrientation.VERTICAL
                legend.isWordWrapEnabled = true

                data = pieData
                invalidate()
            }
        }



    }

}