package com.example.banking

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.banking.models.CardState

@Composable
fun getColorByState(state: CardState): Color {
    return when (state) {
        CardState.EXECUTED -> colorResource(id = R.color.executed_color)
        CardState.DECLINED -> colorResource(id = R.color.declined_color)
        CardState.IN_PROGRESS -> colorResource(id = R.color.in_progress_color)
    }
}