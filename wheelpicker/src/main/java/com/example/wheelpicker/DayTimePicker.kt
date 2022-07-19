package com.example.wheelpicker

import android.content.Context
import com.ait.core.wheelpicker.WheelPickerActionSheet

class DayTimePicker(context: Context) : WheelPickerActionSheet<DayTimePickerView>(context) {
    init {
        setPickerView(DayTimePickerView(context))
    }
}