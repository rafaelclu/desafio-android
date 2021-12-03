package com.picpay.desafio.android.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.picpay.desafio.android.presentation.util.Message

abstract class BaseActivity<VB : ViewBinding>(
    private val viewBindingInflater: (LayoutInflater) -> VB
) : AppCompatActivity() {

    lateinit var binding: VB
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
    }

    fun show(message: Message) {
        message.show(this)
    }

}