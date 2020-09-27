package com.test.newsapp.data.common

import android.text.Editable

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)