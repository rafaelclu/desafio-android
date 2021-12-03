package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.local.db.PicPayDatabase
import com.picpay.desafio.android.data.local.db.PicPayDatabase.Companion.createDatabase
import com.picpay.desafio.android.data.local.db.PicPayDatabase.Companion.getDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single { get<PicPayDatabase>().userDao() }

    single { getDatabase(context = androidContext()) }


}