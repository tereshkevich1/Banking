package com.example.banking.presentation.accounts_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.banking.R

@Composable
fun FloatingActionButtonBox(
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    floatingButtonPadding: Dp
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = floatingButtonPadding),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
            containerColor = containerColor,
            contentColor = contentColor
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "plus",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}