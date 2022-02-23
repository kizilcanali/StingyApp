package com.alikizilcan.stingyapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.alikizilcan.stingyapp.R
import com.alikizilcan.stingyapp.databinding.FragmentHomeBinding
import com.alikizilcan.stingyapp.infra.base.BaseFragment
import com.alikizilcan.stingyapp.infra.colorsList
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.PercentFormatter
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

        viewModel.categoriesData.observe(viewLifecycleOwner) { list ->
            val pieDataSet = PieDataSet(list, "")
            pieDataSet.valueTextSize = 12f
            pieDataSet.colors = colorsList
            pieDataSet.sliceSpace = 3f

            val pieData = PieData(pieDataSet)
            pieData.setDrawValues(true)
            pieData.setValueFormatter(PercentFormatter())

            with(binding.pieChart) {
                setUsePercentValues(true)
                description.text = ""
                isDrawHoleEnabled = true
                setHoleColor(ContextCompat.getColor(requireContext(), R.color.transaction_bg))
                setTouchEnabled(true)
                setDrawEntryLabels(false)
                setExtraOffsets(5f, 10f, 5f, 10f)
                animateY(1400, Easing.EaseInOutQuad)
                transparentCircleRadius = 0f
                isRotationEnabled = false
                legend.orientation = Legend.LegendOrientation.HORIZONTAL
                legend.isWordWrapEnabled = true
                legend.form = Legend.LegendForm.CIRCLE

                data = pieData
                invalidate()
            }
        }
        viewModel.totalExpense.observe(viewLifecycleOwner){
            binding.pieChart.centerText = "Exp: ${viewModel.totalExpense.value} \n Inc: ${viewModel.totalIncome.value}"
        }
        viewModel.totalIncome.observe(viewLifecycleOwner){
            //if null check
            binding.pieChart.centerText = "Exp: ${viewModel.totalExpense.value} \n Inc: ${viewModel.totalIncome.value}"
        }

    }
}