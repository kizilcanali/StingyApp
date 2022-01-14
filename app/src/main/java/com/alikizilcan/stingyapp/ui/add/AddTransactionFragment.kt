package com.alikizilcan.stingyapp.ui.add

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alikizilcan.stingyapp.databinding.FragmentAddTransactionBinding
import com.alikizilcan.stingyapp.infra.di.TYPE
import com.alikizilcan.stingyapp.infra.navigation.NavigationObserver
import dagger.hilt.android.AndroidEntryPoint
import java.lang.NumberFormatException
import java.util.*

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

        setupWm()
        return binding.root
    }

    private fun setupWm() {

        // Navigation make [  ]
        var installmentCount = 0

        setupDate()

        binding.saveButton.setOnClickListener {

            if (binding.installmentMonthInputin.text.toString() == "") {
                try {
                    installmentCount = 0
                } catch (e: NumberFormatException) {
                    println(e.localizedMessage)
                }
            } else {
                installmentCount = Integer.parseInt(binding.installmentMonthInputin.text.toString())
            }

            viewModel.addTransaction(
                name = binding.transactionNameInputin.text.toString(),
                amount = binding.transactionAmountInputin.text.toString().toDouble(),
                date = stringDate!!,
                category = binding.categoryList.text.toString(),
                type = binding.typeList.text.toString(),
                installmentCount = installmentCount,
                navDirections = AddTransactionFragmentDirections.actionAddTransactionFragmentToTransactionsFragment()
            )
        }
    }

    private fun setupDate() {
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        binding.transactionDateInputButton.setOnClickListener {
            val pickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, mYear, mMonth, mDay ->
                    binding.showDateText.text = "$mDay/${mMonth + 1}/$mYear"
                    stringDate = " $mDay/${mMonth + 1}/$mYear "
                }, year, month, day
            )
            pickerDialog.show()
        }
    }

    override fun onResume() {
        super.onResume()

        val dropdownAdapter = DropdownItemAdapter(requireContext(), viewModel.transactionCategories)
        binding.categoryList.setAdapter(dropdownAdapter)

        val dropdownTypeAdapter =
            DropdownTypeAdapter(requireContext(), listOf(TYPE.INCOME, TYPE.EXPENSE))
        binding.typeList.setAdapter(dropdownTypeAdapter)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationObserver.observeNavigation(
            viewModel.navigation,
            findNavController(),
            viewLifecycleOwner
        )

    }
}