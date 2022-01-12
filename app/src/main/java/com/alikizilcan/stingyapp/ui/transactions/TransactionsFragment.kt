package com.alikizilcan.stingyapp.ui.transactions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alikizilcan.stingyapp.R
import com.alikizilcan.stingyapp.databinding.FragmentAddTransactionBinding
import com.alikizilcan.stingyapp.databinding.FragmentTransactionsBinding

class TransactionsFragment : Fragment() {

    private lateinit var _binding: FragmentTransactionsBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        _binding.lifecycleOwner = viewLifecycleOwner
        //Will add wm

        return binding.root
    }
}