package com.alikizilcan.stingyapp.ui.add

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.alikizilcan.stingyapp.R
import com.alikizilcan.stingyapp.infra.di.TYPE

class DropdownTypeAdapter(private val mContext: Context,
                          private val itemsList: List<TYPE>
) : ArrayAdapter<TYPE>(mContext, 0, itemsList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var dropdownItemView = convertView

        if (dropdownItemView == null) {
            dropdownItemView = LayoutInflater.from(mContext).inflate(R.layout.dropdown_type_item, parent, false)
        }
        val type = itemsList[position]


        val typeText: TextView = dropdownItemView!!.findViewById(R.id.typeDropdownName)
        typeText.text = type.toString()

        return dropdownItemView

    }
}