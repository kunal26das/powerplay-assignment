/*
 * Copyright (c) 2020.
 */

package `in`.getpowerplay.assignment.utils

import java.text.SimpleDateFormat
import java.util.*

object Calendar {

    private const val FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss"
    private val LOCALE_INDIA = Locale("en", "in")
    private val simpleDateFormat = SimpleDateFormat(FORMAT_DATE_TIME, LOCALE_INDIA)
    val currentDate
        get() = simpleDateFormat.format(java.util.Calendar.getInstance().time).toString()

}