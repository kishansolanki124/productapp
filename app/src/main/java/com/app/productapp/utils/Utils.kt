package com.app.productapp.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.productapp.R
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun setRecyclerViewLayoutManager(recyclerView: RecyclerView) {
    val layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.layoutManager = layoutManager
    recyclerView.itemAnimator = DefaultItemAnimator()
    recyclerView.isNestedScrollingEnabled = true
    //recyclerView.addItemDecorationVertical()
}

fun RecyclerView.addItemDecorationVertical() {
    this.addItemDecoration(
        DividerItemDecoration(
            this.context, DividerItemDecoration.VERTICAL
        )
    )
}

fun Activity.showSnackBar(message: String?) {
    if (null != message) {
        hideKeyboard(this)
        Snackbar.make(
            this.findViewById(android.R.id.content),
            message, Snackbar.LENGTH_SHORT
        ).show()
    }
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun hideKeyboard(activity: Activity) {
    val imm =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = activity.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun EditText.setActionNext() {
    this.imeOptions = EditorInfo.IME_ACTION_NEXT
    this.setRawInputType(InputType.TYPE_CLASS_TEXT)
}

@SuppressLint("SimpleDateFormat")
fun getDate(milliSeconds: Long, dateFormat: String?): String {
    // Create a DateFormatter object for displaying date in specified format.
    val formatter = SimpleDateFormat(dateFormat)

    // Create a calendar object that will convert the date and time value in milliseconds to date.
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return try {
        formatter.format(calendar.time)
    } catch (e: Exception) {
        ""
    }
}

private var pgDialog: Dialog? = null

fun Context.showProgressDialog() {
    if (pgDialog == null) {
        pgDialog = getProgressDialog(this)
        pgDialog?.show()
    }
}

fun getProgressDialog(context: Context): Dialog? {
    val progressDialog = Dialog(context)
    val view: View =
        LayoutInflater.from(context).inflate(R.layout.dialog_progress, null, false)
    progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    progressDialog.setContentView(view)
    progressDialog.setCancelable(false)
    progressDialog.setCanceledOnTouchOutside(false)
    val window = progressDialog.window
    if (window != null) {
        window.setBackgroundDrawable(
            ContextCompat.getDrawable(
                context,
                android.R.color.transparent
            )
        )
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }
    return progressDialog
}

fun dismissProgressDialog() {
    if (pgDialog != null) {
        pgDialog?.dismiss()
        pgDialog = null
    }
}

fun Context.isConnected(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val n = cm.activeNetwork
        if (n != null) {
            val nc = cm.getNetworkCapabilities(n)
            //It will check for both wifi and cellular network
            return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI
            )
        }
        return false
    } else {
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}
