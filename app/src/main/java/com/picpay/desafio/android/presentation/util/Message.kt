package com.picpay.desafio.android.presentation.util

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.picpay.desafio.android.R

interface Message {
    fun show(context: Context)
}

sealed class Display {

    class Toast(val message: String, val length: Length = Length.SHORT) : Display() {
        enum class Length(val duration: Int) {
            SHORT(android.widget.Toast.LENGTH_LONG), LONG(android.widget.Toast.LENGTH_LONG);
        }
    }

    class CenterDialog(val title: String? = null, val message: String) : Display()
}

class DisplayMessage(private val display: Display) : Message {

    override fun show(context: Context) {
        when (display) {
            is Display.Toast -> {
                Toast.makeText(context, display.message, display.length.duration).show()
            }

            is Display.CenterDialog ->
                AlertDialog.Builder(context)
                    .setTitle(display.title)
                    .setMessage(display.message)
                    .setPositiveButton(R.string.ok) { _, _ -> }
                    .create()
                    .show()
        }
    }

}

object UnknownErrorMessage : Message {
    override fun show(context: Context) {
        DisplayMessage(Display.Toast(context.getString(R.string.unknown_error))).show(context)
    }
}

object NoInternetMessage : Message {
    override fun show(context: Context) {
        DisplayMessage(
            Display.CenterDialog(
                context.getString(R.string.no_internet_error_title),
                context.getString(R.string.no_internet_error_message)
            )
        ).show(context)
    }
}