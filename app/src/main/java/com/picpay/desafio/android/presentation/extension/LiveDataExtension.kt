package com.picpay.desafio.android.presentation.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.picpay.desafio.android.presentation.util.Event

fun <T : Event<R>, R> LiveData<T>.observeNotHandled(
    lifecycleOwner: LifecycleOwner, block: (R) -> Unit
) {
    this.observe(lifecycleOwner) { event ->
        event.getContentIfNotHandled()?.let { block(it) }
    }
}