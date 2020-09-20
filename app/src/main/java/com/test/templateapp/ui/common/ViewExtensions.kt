package com.test.templateapp.ui.common

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.test.templateapp.R

fun Fragment.showErrorBar(msg: String?) =
    view?.let { Snackbar.make(it,msg ?: getString(R.string.network_error), Snackbar.LENGTH_LONG).show() }
