package com.picpay.desafio.android.data.extension

import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.isConnectionException() = when (this) {
    is ConnectException,
    is SocketTimeoutException,
    is NoRouteToHostException,
    is UnknownHostException -> true
    else -> false
}