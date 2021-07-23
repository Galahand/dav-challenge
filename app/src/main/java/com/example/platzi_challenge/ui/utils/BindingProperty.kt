package com.example.platzi_challenge.ui.utils

import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentBinding<T: ViewDataBinding>(@LayoutRes private val resId: Int): ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
        binding ?: inflate(thisRef).also { binding = it }

    private fun inflate(fragment: Fragment): T =
        DataBindingUtil.inflate(LayoutInflater.from(fragment.context), resId, null, false)
}