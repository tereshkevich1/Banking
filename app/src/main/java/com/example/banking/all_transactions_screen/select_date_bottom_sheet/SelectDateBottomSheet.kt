@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.banking.all_transactions_screen.select_date_bottom_sheet

import android.content.Context
import android.util.Log
import android.view.ContextThemeWrapper
import android.widget.CalendarView
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.banking.R
import com.example.banking.create_transaction_screen.TextFieldWithLabel
import com.example.banking.ui.theme.BankingTheme

@Composable
fun SelectDateBottomSheet(
    onDismiss: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val containerColor = colorResource(id = R.color.surface_background_color)
    ModalBottomSheet(
        onDismissRequest = onDismiss, sheetState = modalBottomSheetState,
        containerColor = containerColor,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        DateRangeColumn()
    }
}

@Composable
fun DateRangeColumn() {
    val innerPadding = dimensionResource(id = R.dimen.inner_padding)
    var showCalendar by remember {
        mutableStateOf(true)
    }

    Column(modifier = Modifier.padding(horizontal = innerPadding)) {
        TextFieldWithLabel(
            "name", "select startDate", {}, Modifier,
            readOnly = true,
            trailingIcon = {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.calendar),
                    contentDescription = "calendar"
                )
            },
            onItemClick = { showCalendar = true},
            unfocusedIndicatorColor = colorResource(id = R.color.unfocused_indicator_date_field_color),
        )
        if (showCalendar) {
            AndroidView(
                { CalendarView(ContextThemeWrapper(it, R.style.MyCalendarView)) },
                modifier = Modifier.wrapContentWidth(),
                update = { views ->
                    views.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
                    }
                }
            )
        }
        TextFieldWithLabel(
            "name", "select startDate", {}, Modifier,
            readOnly = true,
            trailingIcon = {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.calendar),
                    contentDescription = "calendar"
                )
            },
            unfocusedIndicatorColor = colorResource(id = R.color.unfocused_indicator_date_field_color),
        )
    }
}


@Composable
@Preview
fun TextFieldWithLabelPreview() {
    BankingTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.surface_background_color)
        ) {
            Box(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.inner_padding)),
                contentAlignment = Alignment.Center
            ) {
                TextFieldWithLabel(
                    "name", "select startDate", {}, Modifier, readOnly = true,
                    trailingIcon =
                    {
                        Icon(
                            ImageVector.vectorResource(id = R.drawable.calendar),
                            contentDescription = "calendar"
                        )
                    },
                    unfocusedIndicatorColor = colorResource(id = R.color.unfocused_indicator_date_field_color),
                )
            }
        }
    }
}