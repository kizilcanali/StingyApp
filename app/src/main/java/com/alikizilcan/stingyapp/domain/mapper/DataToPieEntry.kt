package com.alikizilcan.stingyapp.domain.mapper

import com.github.mikephil.charting.data.PieEntry
import javax.inject.Inject

class DataToPieEntry @Inject constructor() {
    fun mapFromData(data: Map<String, Double>) : ArrayList<PieEntry>{
        return data.map {
            PieEntry(
                it.value.toFloat(),
                it.key
            )
        } as ArrayList<PieEntry>
    }
}