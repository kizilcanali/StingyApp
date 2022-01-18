package com.alikizilcan.stingyapp.ui.add

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.alikizilcan.stingyapp.R
import com.alikizilcan.stingyapp.infra.Category

class DropdownItemAdapter(
    private val mContext: Context,
    private val itemsList: List<Category>
) : ArrayAdapter<Category>(mContext, 0, itemsList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var dropdownItemView = convertView

        if (dropdownItemView == null) {
            dropdownItemView = LayoutInflater.from(mContext).inflate(R.layout.dropdown_category_item, parent, false)
        }
        val category = itemsList[position]

        val categoryName: TextView = dropdownItemView!!.findViewById(R.id.transactionCategoryTextDropdown)
        categoryName.text = category.name

        val categoryIcon: ImageView = dropdownItemView.findViewById(R.id.categoryIcon)
        categoryIcon.setImageResource(category.icon!!)


        return dropdownItemView
    }

}