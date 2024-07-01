package com.example.banking.presentation.all_transactions_screen.select_date_bottom_sheet

import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.banking.R
import java.util.Calendar

@Composable
fun CalendarViewComposable(
    modifier: Modifier = Modifier,
    onDateSelected: (year: Int, month: Int, dayOfMonth: Int) -> Unit,
    minDate: Long = Calendar.getInstance().apply {
        set(Calendar.YEAR, 1970)
        set(Calendar.MONTH, Calendar.JANUARY)
        set(Calendar.DAY_OF_MONTH, 1)
    }.timeInMillis,
    maxDate: Long = System.currentTimeMillis(),
    selectedDate: Long = System.currentTimeMillis()
) {
    AndroidView(
        factory = { context ->
            CalendarView(ContextThemeWrapper(context, R.style.MyCalendarView))
        },
        modifier = modifier,
        update = { calendarView ->
            calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                onDateSelected(year, month, dayOfMonth)
            }
            calendarView.date = selectedDate
            calendarView.maxDate = maxDate
            calendarView.minDate = minDate
        }
    )
}