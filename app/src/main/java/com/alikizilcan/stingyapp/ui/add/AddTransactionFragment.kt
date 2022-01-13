package com.alikizilcan.stingyapp.ui.add

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alikizilcan.stingyapp.databinding.FragmentAddTransactionBinding
import com.alikizilcan.stingyapp.infra.navigation.NavigationObserver
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.time.Duration.Companion.days

@AndroidEntryPoint
class AddTransactionFragment : Fragment() {

    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddTransactionViewModel by viewModels()
    private val navigationObserver = NavigationObserver()

    var stringDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val dropdownAdapter = DropdownItemAdapter(requireContext(), viewModel.transactionCategories)
        binding.categoryList.setAdapter(dropdownAdapter)

        //Dropdown Type List will make [  ]


        setupWm()
        return binding.root
    }

    private fun setupWm(){

        // Navigation make [  ]
        setupDate()

        binding.saveButton.setOnClickListener {
            viewModel.addTransaction(
                binding.transactionNameInputin.text.toString(),
                binding.transactionAmountInputin.text.toString().toDouble(),
                stringDate!!,
                binding.categoryList.text.toString(),
                binding.typeList.text.toString(),
                binding.installmentMonthInputin.text.toString().toInt()
            )
        }
    }

    private fun setupDate(){
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        binding.transactionDateInputButton.setOnClickListener {
            val pickerDialog = DatePickerDialog(requireContext(),
                DatePickerDialog.OnDateSetListener { _, mYear, mMonth, mDay ->
                    binding.showDateText.setText("$mDay/${mMonth + 1}/$mYear")
                    stringDate = " $mDay/${mMonth + 1}/$mYear "
                }, year, month, day)
            pickerDialog.show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationObserver.observeNavigation(viewModel.navigation, findNavController(), viewLifecycleOwner)

    }
}