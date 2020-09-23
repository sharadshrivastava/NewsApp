package com.test.templateapp.ui.common

import android.content.Context

fun pxFromDp(context: Context?, dp: Float): Float {
    return dp * context?.resources?.displayMetrics?.density!!
}

fun DpFromPx(context: Context?, px: Float): Float {
    return px / context?.resources?.displayMetrics?.density!!;
}