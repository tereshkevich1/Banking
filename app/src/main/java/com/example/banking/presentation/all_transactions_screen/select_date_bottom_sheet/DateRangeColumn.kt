package com.example.banking.presentation.all_transactions_screen.select_date_bottom_sheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.banking.R
import com.example.banking.presentation.create_transaction_screen.OkButton

@Composable
fun DateRangeColumn(
    onSubmitButtonClick: (startDate: Long, endDate: Long) -> Unit,
    viewModel: DateRangeViewModel
) {
    val innerPadding = dimensionResource(id = R.dimen.inner_padding)
    val bottomSheetPadding = dimensionResource(id = R.dimen.bottom_sheet_calendar_padding)

    val showStartDateCalendar = viewModel.showStartDateCalendar
    val showEndDateCalendar = viewModel.showEndDateCalendar
    val hasError = viewModel.hasError

    val startDate = viewModel.startDate
    val endDate = viewModel.endDate

    val startDateLabel = stringResource(id = R.string.start_date_title)
    val endDateLabel = stringResource(id = R.string.end_date_title)

    Column(
        modifier = Modifier.padding(
            start = innerPadding,
            end = innerPadding,
            bottom = bottomSheetPadding
        )
    ) {
        DateButtonWithIcon(showStartDateCalendar, hasError, startDate, startDateLabel) {
            viewModel.startDateCalendar()
        }

        AnimatedVisibility(
            visible = showStartDateCalendar,
            enter = fadeIn(tween(700, easing = LinearOutSlowInEasing)),
            exit = fadeOut(tween(700, easing = FastOutLinearInEasing))
        ) {
            if (showStartDateCalendar) {
                CalendarViewComposable(onDateSelected = { year, month, dayOfMonth ->
                    viewModel.onDateSelected(year, month, dayOfMonth, true)
                }, maxDate = viewModel.getMaxDate(), selectedDate = viewModel.selectedStartDate)
            }
        }

        DateButtonWithIcon(showEndDateCalendar, hasError, endDate, endDateLabel) {
            viewModel.endDateCalendar()
        }

        AnimatedVisibility(
            visible = showEndDateCalendar,
            enter = fadeIn(tween(700, easing = LinearOutSlowInEasing)),
            exit = fadeOut(tween(700, easing = FastOutLinearInEasing))
        ) {
            if (showEndDateCalendar) {
                CalendarViewComposable(onDateSelected = { year, month, dayOfMonth ->
                    viewModel.onDateSelected(year, month, dayOfMonth, false)
                }, minDate = viewModel.getMinDate(), selectedDate = viewModel.selectedEndDate)
            }
        }

        OkButton(onClick = {
            viewModel.onSubmit()
            if (!viewModel.hasError) {
                onSubmitButtonClick(viewModel.selectedStartDate, viewModel.selectedEndDate)
            }
        }, modifier = Modifier.padding(vertical = innerPadding), isButtonEnabled = true)
    }
}