package com.kc.debug.debugview

import androidx.lifecycle.MutableLiveData

/**
 * @data on 2021/6/10 2:48 下午
 * @auther
 * @describe
 */
class InitLiveData<T>(init: T) : MutableLiveData<T>(init) {
    override fun getValue(): T {
        return super.getValue()!!
    }

    override fun setValue(value: T) {
        super.setValue(value)
    }
}