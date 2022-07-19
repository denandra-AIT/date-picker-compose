package com.ait.core.wheelpicker

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.ait.core.R
import com.ait.core.databinding.TriplePickerViewBinding
import com.ait.core.wheelpicker.core.BaseWheelPickerView
import com.ait.core.wheelpicker.core.TextWheelAdapter
import com.ait.core.wheelpicker.core.TextWheelPickerView
import java.text.SimpleDateFormat
import java.util.*

class DayTimePickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), BaseWheelPickerView.WheelPickerViewListener {

    interface Listener {
        fun didSelectData(day: Int, month: Int, years: Int)
    }

    private val month = mutableListOf("January", "February", "March", "April", "Mei", "June", "July", "August", "September", "Oktober", "November", "December")

    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("yyyy")
    private val currentDate = sdf.format(Date()).toInt()
    private val yearRange = 1960 until currentDate+1

    private val dayPickerView: TextWheelPickerView
    private val monthPickerView: TextWheelPickerView
    private val minutePickerView: TextWheelPickerView

    private var listener: Listener? = null

    fun setWheelListener(listener: Listener) {
        this.listener = listener
    }

    private var yearsList: List<Int> = yearRange.toList()

    var day: Int
        set(value) {
            dayPickerView.selectedIndex = value - 1
        }
        get() = dayPickerView.selectedIndex + 1

    var months: Int
        set(value) {
            monthPickerView.selectedIndex = value - 1
        }
        get() = monthPickerView.selectedIndex + 1

    var year: Int
        set(value) {
            monthPickerView.selectedIndex = yearsList.indexOf(value)
        }
        get() = yearsList.getOrNull(minutePickerView.selectedIndex) ?: 1960

    var isCircular: Boolean = false
        set(value) {
            field = value
            dayPickerView.isCircular = value
            monthPickerView.isCircular = value
            minutePickerView.isCircular = value
        }

    private val dayAdapter = TextWheelAdapter()
    private val monthAdapter = TextWheelAdapter()
    private val minuteAdapter = TextWheelAdapter()

    private val binding: TriplePickerViewBinding =
        TriplePickerViewBinding.inflate(LayoutInflater.from(context), this)

    override fun setHapticFeedbackEnabled(hapticFeedbackEnabled: Boolean) {
        super.setHapticFeedbackEnabled(hapticFeedbackEnabled)
        dayPickerView.isHapticFeedbackEnabled = hapticFeedbackEnabled
        monthPickerView.isHapticFeedbackEnabled = hapticFeedbackEnabled
        minutePickerView.isHapticFeedbackEnabled = hapticFeedbackEnabled
    }

    init {
        dayPickerView = binding.leftPicker
        dayPickerView.setAdapter(dayAdapter)
        dayAdapter.values = (1..31).map {
            TextWheelPickerView.Item(
                "$it",
                "$it"
            )
        }
        monthPickerView = binding.midPicker
        monthPickerView.setAdapter(monthAdapter)
        monthAdapter.values = (month).map { TextWheelPickerView.Item(it, it) }

        minutePickerView = binding.rightPicker
        minutePickerView.setAdapter(minuteAdapter)
        minuteAdapter.values =
            (yearRange).map {
                TextWheelPickerView.Item("$it", "$it")
            }

        dayPickerView.setWheelListener(this)
        monthPickerView.setWheelListener(this)
        minutePickerView.setWheelListener(this)
    }

    // region BaseWheelPickerView.WheelPickerViewListener
    override fun didSelectItem(picker: BaseWheelPickerView, index: Int) {
        listener?.didSelectData(day, months, year)
    }
    // endregion
}