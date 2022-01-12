package com.alikizilcan.stingyapp.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alikizilcan.stingyapp.databinding.FragmentAddTransactionBinding
import com.alikizilcan.stingyapp.infra.navigation.NavigationObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionFragment : Fragment() {

    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddTransactionViewModel by viewModels()
    private val navigationObserver = NavigationObserver()


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

        //Dropdown List will make [  ]


        setupWm()
        return binding.root
    }

    fun setupWm(){

        // Date Format will make [  ]
        // Navigation make [  ]

        binding.saveButton.setOnClickListener {
            viewModel.addTransaction(
                binding.transactionNameInputin.text.toString(),
                binding.transactionAmountInputin.text.toString().toDouble(),
                binding.transactionDateInput.toString(),
                binding.categoryList.text.toString(),
                binding.typeList.text.toString(),
                binding.installmentMonthInputin.text.toString().toInt()
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationObserver.observeNavigation(viewModel.navigation, findNavController(), viewLifecycleOwner)

    }
}