package com.example.library

import android.content.res.Resources
import android.util.TypedValue

fun Float.dp2px() : Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    ).toInt()
}