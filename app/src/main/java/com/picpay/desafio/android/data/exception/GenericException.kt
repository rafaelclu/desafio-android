package com.picpay.desafio.android.data.exception

import com.picpay.desafio.android.BuildConfig


abstract class PicPayException : RuntimeException {
    constructor(message: String, throwable: Throwable) : super(message, throwable)
    constructor(message: String) : super(message)
    constructor(throwable: Throwable) : super(throwable)
    constructor() : super()

    override fun fillInStackTrace(): Throwable {
        if (BuildConfig.DEBUG)
            return super.fillInStackTrace()

        return this
    }

}