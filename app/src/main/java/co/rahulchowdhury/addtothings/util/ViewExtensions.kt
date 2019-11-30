package co.rahulchowdhury.addtothings.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import co.rahulchowdhury.addtothings.R
import com.google.android.material.snackbar.Snackbar


fun Fragment.showSnackbar(messageId: Int) {
    view?.let {
        val snackbar = Snackbar.make(
            it,
            getString(messageId),
            Snackbar.LENGTH_LONG
        )

        snackbar.view.apply {
            setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorAccent))

            val textView = findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(ContextCompat.getColor(context!!, android.R.color.white))
            textView.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorAccent))
        }

        snackbar.show()
    }
}

fun Fragment.hideKeyboard() {
    context?.let {
        val inputMethodManager: InputMethodManager =
            it.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}
