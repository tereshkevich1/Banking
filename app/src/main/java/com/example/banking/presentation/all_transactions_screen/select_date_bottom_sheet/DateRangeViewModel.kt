package com.example.banking.presentation.all_transactions_screen.select_date_bottom_sheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DateRangeViewModel : ViewModel() {
    var showStartDateCalendar by mutableStateOf(false)
    var showEndDateCalendar by mutableStateOf(false)
    var hasError by mutableStateOf(false)
    var startDate by mutableStateOf("")
    var endDate by mutableStateOf("")

    private val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    private val calendar = Calendar.getInstance()
    var selectedStartDate: Long by mutableLongStateOf(calendar.timeInMillis)
    var selectedEndDate: Long by mutableLongStateOf(calendar.timeInMillis)

    fun getMinDate(): Long {
        return if (startDate.isEmpty()) {
            Calendar.getInstance().apply {
                set(Calendar.YEAR, 1970)
                set(Calendar.MONTH, Calendar.JANUARY)
                set(Calendar.DAY_OF_MONTH, 1)
            }.timeInMillis
        } else {
            val calendar = Calendar.getInstance()
            calendar.time = dateFormat.parse(startDate)!!
            calendar.timeInMillis
        }
    }

    fun getMaxDate(): Long {
        return if (endDate.isEmpty()) {
            System.currentTimeMillis()
        } else {
            val calendar = Calendar.getInstance()
            calendar.time = dateFormat.parse(endDate)!!
            calendar.timeInMillis
        }
    }

    fun startDateCalendar() {
        showStartDateCalendar = !showStartDateCalendar
        showEndDateCalendar = false
    }

    fun endDateCalendar() {
        showEndDateCalendar = !showEndDateCalendar
        showStartDateCalendar = false
    }

    fun onDateSelected(year: Int, month: Int, dayOfMonth: Int, isStartDate: Boolean) {
        calendar.set(year, month, dayOfMonth)
        val date = dateFormat.format(calendar.time)
        if (isStartDate) {
            startDate = date
            selectedStartDate = calendar.timeInMillis
        } else {
            endDate = date
            selectedEndDate = calendar.timeInMillis
        }
    }

    fun onSubmit() {
        hasError = startDate.isEmpty() || endDate.isEmpty()
    }
}