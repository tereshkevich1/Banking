package com.example.banking.domain.use_case

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ConvertLongToDateUseCase @Inject constructor() {
    operator fun invoke(dateLong: Long): String {
        val date = Date(dateLong)
        val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return format.format(date)
    }
}